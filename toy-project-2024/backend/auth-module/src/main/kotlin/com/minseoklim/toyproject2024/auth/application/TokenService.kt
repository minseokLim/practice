package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.AccessToken
import com.minseoklim.toyproject2024.auth.domain.AccessTokenRepository
import com.minseoklim.toyproject2024.auth.domain.RefreshToken
import com.minseoklim.toyproject2024.auth.domain.RefreshTokenRepository
import com.minseoklim.toyproject2024.auth.domain.RefreshTokenValidator
import com.minseoklim.toyproject2024.auth.domain.TokenIdGenerator
import com.minseoklim.toyproject2024.auth.domain.TokenParser
import com.minseoklim.toyproject2024.auth.domain.TokenProvider
import com.minseoklim.toyproject2024.auth.dto.TokenRequest
import com.minseoklim.toyproject2024.auth.dto.TokenResponse
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TokenService(
    private val tokenProvider: TokenProvider,
    private val tokenParser: TokenParser,
    private val accessTokenRepository: AccessTokenRepository,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val refreshTokenValidator: RefreshTokenValidator
) {
    fun createToken(authentication: Authentication): TokenResponse {
        val tokenId = TokenIdGenerator.generate()
        val accessToken = tokenProvider.createAccessToken(authentication, tokenId)
        accessTokenRepository.save(
            AccessToken(
                id = tokenId,
                memberId = authentication.name.toInt(),
                content = accessToken
            )
        )

        val refreshToken = tokenProvider.createRefreshToken(tokenId)
        refreshTokenRepository.save(
            RefreshToken(
                id = tokenId,
                memberId = authentication.name.toInt(),
                content = refreshToken
            )
        )

        return TokenResponse(accessToken, refreshToken)
    }

    fun refreshToken(request: TokenRequest): TokenResponse {
        refreshTokenValidator.validate(request.refreshToken)

        deleteAccessToken(request.accessToken)
        deleteRefreshToken(request.refreshToken)

        val authentication = tokenParser.extractAuthentication(request.accessToken)
        return createToken(authentication)
    }

    fun deleteAccessToken(accessToken: String) {
        val accessTokenId = tokenParser.extractId(accessToken)
        val accessTokenEntity = accessTokenRepository.findById(accessTokenId)
            .orElseThrow { throw BadCredentialsException("Invalid access token") }
        accessTokenEntity.delete()
    }

    fun deleteRefreshToken(refreshToken: String) {
        val refreshTokenId = tokenParser.extractId(refreshToken)
        val refreshTokenEntity = refreshTokenRepository.findById(refreshTokenId)
            .orElseThrow { throw BadCredentialsException("Invalid refresh token") }
        refreshTokenEntity.delete()
    }

    fun deleteAllAccessToken(memberId: Int) {
        accessTokenRepository.findAllByMemberId(memberId).forEach {
            it.delete()
        }
    }

    fun deleteAllRefreshToken(memberId: Int) {
        refreshTokenRepository.findAllByMemberId(memberId).forEach {
            it.delete()
        }
    }
}
