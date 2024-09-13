package com.minseoklim.toyproject2024.auth.dto.ui

import com.minseoklim.toyproject2024.auth.dto.application.RefreshTokenOutput

data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun of(output: RefreshTokenOutput): RefreshTokenResponse {
            return RefreshTokenResponse(
                accessToken = output.accessToken,
                refreshToken = output.refreshToken
            )
        }
    }
}
