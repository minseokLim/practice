package com.minseoklim.toyproject2024.location.ui.dto

import com.minseoklim.toyproject2024.location.application.dto.RegisterLocationInput

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
