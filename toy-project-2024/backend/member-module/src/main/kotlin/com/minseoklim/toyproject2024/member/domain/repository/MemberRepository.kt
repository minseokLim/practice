package com.minseoklim.toyproject2024.member.domain.repository

import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface MemberRepository : JpaRepository<Member, Int>, QuerydslPredicateExecutor<Member> {
    fun findByLoginIdValue(loginId: String): Member?

    fun countByLoginIdValue(loginId: String): Int

    @Query(
        """
            SELECT m FROM Member m JOIN m.socialLinks.values s
            WHERE s.socialType = :socialType AND s.socialId.value = :socialId
        """
    )
    fun findBySocialTypeAndSocialId(
        socialType: SocialType,
        socialId: String
    ): Member?
}
