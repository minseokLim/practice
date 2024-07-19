package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.repository.TokenRepository
import com.minseoklim.toyproject2024.auth.domain.service.TokenParser
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteTokenService(
    private val tokenParser: TokenParser,
    private val tokenRepository: TokenRepository
) {
    fun deleteToken(accessToken: String, refreshToken: String) {
        val accessTokenId = tokenParser.extractId(accessToken)
        val refreshTokenId = tokenParser.extractId(refreshToken)
        if (accessTokenId != refreshTokenId) {
            throw BadCredentialsException("Invalid token set")
        }
        val token = tokenRepository.findById(accessTokenId)
            .orElseThrow { throw BadCredentialsException("Invalid token set") }
        token.delete()
    }

    fun deleteAllToken(memberId: Int) {
        tokenRepository.findAllByMemberId(memberId).forEach {
            it.delete()
        }
    }
}
