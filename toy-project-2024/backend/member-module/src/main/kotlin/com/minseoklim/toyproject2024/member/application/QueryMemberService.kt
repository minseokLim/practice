package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.model.SocialType
import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput
import com.minseoklim.toyproject2024.member.util.MemberFilterParser
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryMemberService(
    private val memberRepository: MemberRepository
) {
    fun list(
        filter: String?,
        pageable: Pageable
    ): Page<QueryMemberOutput> {
        val predicate = MemberFilterParser.parse(filter)
        val members = memberRepository.findAll(predicate, pageable)
        return members.map { QueryMemberOutput.from(it) }
    }

    fun get(id: Long): QueryMemberOutput {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        return QueryMemberOutput.from(member)
    }

    fun findByLoginId(loginId: String): QueryMemberOutput? {
        val member = memberRepository.findByLoginIdValue(loginId)
        return member?.let { QueryMemberOutput.from(it) }
    }

    fun findBySocialTypeAndSocialId(
        socialType: SocialType,
        socialId: String
    ): QueryMemberOutput? {
        val member = memberRepository.findBySocialTypeAndSocialId(socialType, socialId)
        return member?.let { QueryMemberOutput.from(it) }
    }

    fun findAllByIds(ids: Collection<Long>): List<QueryMemberOutput> {
        val members = memberRepository.findAllById(ids)
        return members.map { QueryMemberOutput.from(it) }
    }

    fun findAllNotDeletedExceptId(id: Long): List<QueryMemberOutput> {
        val members = memberRepository.findAllByIdIsNotAndIsDeleted(id, false)
        return members.map { QueryMemberOutput.from(it) }
    }
}
