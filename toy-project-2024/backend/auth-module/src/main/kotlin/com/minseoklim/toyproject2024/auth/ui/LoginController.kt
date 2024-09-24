package com.minseoklim.toyproject2024.auth.ui

import com.minseoklim.toyproject2024.auth.application.LoginService
import com.minseoklim.toyproject2024.auth.dto.ui.LoginRequest
import com.minseoklim.toyproject2024.auth.dto.ui.LoginResponse
import com.minseoklim.toyproject2024.common.util.ClientUtil.getClientIp
import com.minseoklim.toyproject2024.common.util.ClientUtil.getUserAgent
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val loginService: LoginService
) {
    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: LoginRequest,
        servletRequest: HttpServletRequest
    ): ResponseEntity<LoginResponse> {
        val output = loginService.login(request.toInput(), servletRequest.getClientIp(), servletRequest.getUserAgent())
        return ResponseEntity.ok(LoginResponse.from(output))
    }
}
