package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.AccessTokenRepository
import com.minseoklim.toyproject2024.auth.domain.RefreshTokenRepository
import com.minseoklim.toyproject2024.auth.domain.TokenParser
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteTokenService(
    private val tokenParser: TokenParser,
    private val accessTokenRepository: AccessTokenRepository,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun deleteToken(accessToken: String, refreshToken: String) {
        deleteAccessToken(accessToken)
        deleteRefreshToken(refreshToken)
    }

    fun deleteAllToken(memberId: Int) {
        deleteAllAccessToken(memberId)
        deleteAllRefreshToken(memberId)
    }

    private fun deleteAccessToken(accessToken: String) {
        val accessTokenId = tokenParser.extractId(accessToken)
        val accessTokenEntity = accessTokenRepository.findById(accessTokenId)
            .orElseThrow { throw BadCredentialsException("Invalid access token") }
        accessTokenEntity.delete()
    }

    private fun deleteRefreshToken(refreshToken: String) {
        val refreshTokenId = tokenParser.extractId(refreshToken)
        val refreshTokenEntity = refreshTokenRepository.findById(refreshTokenId)
            .orElseThrow { throw BadCredentialsException("Invalid refresh token") }
        refreshTokenEntity.delete()
    }

    private fun deleteAllAccessToken(memberId: Int) {
        accessTokenRepository.findAllByMemberId(memberId).forEach {
            it.delete()
        }
    }

    private fun deleteAllRefreshToken(memberId: Int) {
        refreshTokenRepository.findAllByMemberId(memberId).forEach {
            it.delete()
        }
    }
}
