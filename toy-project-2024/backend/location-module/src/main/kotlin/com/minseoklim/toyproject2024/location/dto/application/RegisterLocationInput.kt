package com.minseoklim.toyproject2024.location.dto.application

import com.minseoklim.toyproject2024.location.domain.model.Location

data class RegisterLocationInput(
    val latitude: Double,
    val longitude: Double
) {
    fun toEntity(memberId: Long): Location {
        return Location(
            latitude = latitude,
            longitude = longitude,
            memberId = memberId
        )
    }
}
