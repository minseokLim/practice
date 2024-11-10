package com.minseoklim.toyproject2024.feed.application

import com.minseoklim.toyproject2024.feed.domain.repository.FeedRepository
import com.minseoklim.toyproject2024.feed.dto.application.QueryFeedOutput
import com.minseoklim.toyproject2024.member.application.QueryMemberService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryFeedService(
    private val feedRepository: FeedRepository,
    private val queryMemberService: QueryMemberService
) {
    fun list(
        cursorId: Long,
        size: Int
    ): List<QueryFeedOutput> {
        val feeds = feedRepository.findAllByIdLessThanOrderByIdDesc(
            cursorId = cursorId,
            pageable = PageRequest.of(0, size)
        )
        val members = queryMemberService.findAllByIds(feeds.map { it.memberId }.toSet())
        val memberIdToMember = members.associateBy { it.id }

        return feeds.filter { memberIdToMember[it.memberId] != null }
            .map { QueryFeedOutput.of(it, memberIdToMember.getValue(it.memberId)) }
    }
}
