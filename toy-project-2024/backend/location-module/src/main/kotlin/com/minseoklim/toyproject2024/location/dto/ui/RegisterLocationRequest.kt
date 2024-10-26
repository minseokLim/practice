package com.minseoklim.toyproject2024.location.dto.ui

import com.minseoklim.toyproject2024.location.dto.application.RegisterLocationInput

data class RegisterLocationRequest(
    val latitude: Double,
    val longitude: Double
) {
    fun toInput(): RegisterLocationInput {
        return RegisterLocationInput(
            latitude = latitude,
            longitude = longitude
        )
    }
}
