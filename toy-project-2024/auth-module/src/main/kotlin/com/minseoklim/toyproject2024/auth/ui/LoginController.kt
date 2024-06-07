package com.minseoklim.toyproject2024.auth.ui

import com.minseoklim.toyproject2024.auth.application.LoginService
import com.minseoklim.toyproject2024.auth.dto.LoginRequest
import com.minseoklim.toyproject2024.auth.dto.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val loginService: LoginService
) {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<TokenResponse> {
        val response = loginService.login(request)
        return ResponseEntity.ok(response)
    }
}
