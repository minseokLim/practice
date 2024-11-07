package com.minseoklim.toyproject2024.location.dto.ui

import com.minseoklim.toyproject2024.location.dto.application.QueryNearbyLocationOutput

data class QueryNearbyLocationResponse private constructor(
    val latitude: Double,
    val longitude: Double,
    val member: MemberResponse
) {
    companion object {
        fun from(output: QueryNearbyLocationOutput): QueryNearbyLocationResponse {
            return QueryNearbyLocationResponse(
                latitude = output.latitude,
                longitude = output.longitude,
                member = MemberResponse.from(output.member)
            )
        }
    }

    data class MemberResponse private constructor(
        val id: Long,
        val name: String
    ) {
        companion object {
            fun from(output: QueryNearbyLocationOutput.MemberOutput): MemberResponse {
                return MemberResponse(
                    id = output.id,
                    name = output.name
                )
            }
        }
    }
}
