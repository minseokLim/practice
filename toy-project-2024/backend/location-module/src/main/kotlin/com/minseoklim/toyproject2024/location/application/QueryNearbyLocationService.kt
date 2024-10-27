package com.minseoklim.toyproject2024.location.application

import com.minseoklim.toyproject2024.location.domain.repository.LocationRepository
import com.minseoklim.toyproject2024.location.dto.application.QueryNearbyLocationOutput
import com.minseoklim.toyproject2024.member.application.QueryMemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryNearbyLocationService(
    private val locationRepository: LocationRepository,
    private val queryMemberService: QueryMemberService
) {
    fun list(
        memberId: Int,
        radius: Int
    ): List<QueryNearbyLocationOutput> {
        val location = locationRepository.findByMemberId(memberId) ?: return emptyList()
        val nearbyLocations = locationRepository.findAllByDistance(location.coordinate, radius)
            .filter { it.memberId != memberId } // 자신 제외

        val memberIdToMember = queryMemberService.findAllByIds(nearbyLocations.map { it.memberId })
            .associateBy { it.id }

        return nearbyLocations.filter { memberIdToMember[it.memberId] != null } // 탈퇴 회원 필터링
            .map { QueryNearbyLocationOutput.of(it, memberIdToMember) }
    }
}
