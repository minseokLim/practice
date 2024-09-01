package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.auth.domain.model.LoginHistory
import com.minseoklim.toyproject2024.auth.domain.repository.LoginHistoryRepository
import com.minseoklim.toyproject2024.auth.dto.application.LoginInput
import com.minseoklim.toyproject2024.auth.dto.application.LoginOutput
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class LoginService(
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val createTokenService: CreateTokenService,
    private val loginHistoryRepository: LoginHistoryRepository,
    private val loginNotifier: LoginNotifier
) {
    fun login(input: LoginInput, clientIp: String, userAgent: String): LoginOutput {
        val authenticationManager = authenticationManagerBuilder.getObject()
        val authentication = authenticationManager.authenticate(input.toAuthentication())
        val token = createTokenService.createToken(authentication)

        val loginDateTime = LocalDateTime.now()
        loginHistoryRepository.save(
            LoginHistory(
                memberId = authentication.name.toInt(),
                tokenId = token.id,
                clientIp = clientIp,
                userAgent = userAgent,
                loginDateTime = loginDateTime
            )
        )
        loginNotifier.notifyLogin(authentication.name.toInt(), clientIp, userAgent, loginDateTime)

        return LoginOutput(token.accessToken, token.refreshToken)
    }
}
