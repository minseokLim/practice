package com.minseoklim.toyproject2024.location.dto.application

import com.minseoklim.toyproject2024.location.domain.model.Location
import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput

data class QueryNearbyLocationOutput private constructor(
    val latitude: Double,
    val longitude: Double,
    val member: MemberOutput
) {
    companion object {
        fun of(
            location: Location,
            memberIdToMember: Map<Long, QueryMemberOutput>
        ): QueryNearbyLocationOutput {
            return QueryNearbyLocationOutput(
                latitude = location.coordinate.y,
                longitude = location.coordinate.x,
                member = MemberOutput(
                    id = location.memberId,
                    name = memberIdToMember[location.memberId]?.name ?: "Unknown"
                )
            )
        }
    }

    data class MemberOutput(
        val id: Long,
        val name: String
    )
}
