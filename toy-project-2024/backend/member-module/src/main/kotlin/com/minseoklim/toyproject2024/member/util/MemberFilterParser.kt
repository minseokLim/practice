package com.minseoklim.toyproject2024.member.util

import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.util.ConsistentHashUtil
import com.minseoklim.toyproject2024.common.util.FilterParser
import com.minseoklim.toyproject2024.common.util.QuerydslUtil.equal
import com.minseoklim.toyproject2024.member.domain.model.QMember.member
import com.minseoklim.toyproject2024.member.domain.model.QMemberRole.memberRole
import com.minseoklim.toyproject2024.member.domain.model.QSocialLink.socialLink
import com.minseoklim.toyproject2024.member.domain.model.Role
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.JPAExpressions

// 컬럼마다 인덱스가 있지 않기 때문에 DB에 부하가 갈 수 있는 구현이다.
// 일단 그냥 연습삼아 이렇게 구현하도록 한다.
object MemberFilterParser : FilterParser() {
    override fun createBooleanExpression(key: String, value: String): BooleanExpression {
        return when (key) {
            "loginId" -> member.loginId.value equal value
            "name" -> member.name.hashedValue equal ConsistentHashUtil.hash(value)
            "email" -> member.email.hashedValue equal ConsistentHashUtil.hash(value)
            "role" -> {
                member.id.`in`(
                    JPAExpressions.select(member.id)
                        .from(member)
                        .join(member.memberRoles.values, memberRole)
                        .where(
                            memberRole.role equal Role.valueOf(value)
                        )
                )
            }

            "socialType" -> {
                member.id.`in`(
                    JPAExpressions.select(member.id)
                        .from(member)
                        .join(member.socialLinks.values, socialLink)
                        .where(
                            socialLink.socialType equal SocialType.valueOf(value)
                        )
                )
            }

            "isDeleted" -> member.isDeleted equal value.toBoolean()
            else -> throw BadRequestException("INVALID_FILTER_KEY", "유효하지 않은 필터 키입니다.")
        }
    }
}
