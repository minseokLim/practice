package com.minseoklim.toyproject2024.auth.ui.dto

import com.minseoklim.toyproject2024.auth.application.dto.RefreshTokenOutput

data class RefreshTokenResponse private constructor(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun from(output: RefreshTokenOutput): RefreshTokenResponse {
            return RefreshTokenResponse(
                accessToken = output.accessToken,
                refreshToken = output.refreshToken
            )
        }
    }
}
