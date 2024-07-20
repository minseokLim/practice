package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.model.LoginHistory
import com.minseoklim.toyproject2024.auth.domain.repository.LoginHistoryRepository
import com.minseoklim.toyproject2024.auth.dto.LoginRequest
import com.minseoklim.toyproject2024.auth.dto.LoginResponse
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LoginService(
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val createTokenService: CreateTokenService,
    private val loginHistoryRepository: LoginHistoryRepository
) {
    fun login(request: LoginRequest, clientIp: String, userAgent: String): LoginResponse {
        val authenticationManager = authenticationManagerBuilder.getObject()
        val authentication = authenticationManager.authenticate(request.toAuthentication())
        val token = createTokenService.createToken(authentication)
        loginHistoryRepository.save(
            LoginHistory(
                memberId = authentication.name.toInt(),
                tokenId = token.id,
                clientIp = clientIp,
                userAgent = userAgent
            )
        )
        return LoginResponse(token.accessToken, token.refreshToken)
    }
}
