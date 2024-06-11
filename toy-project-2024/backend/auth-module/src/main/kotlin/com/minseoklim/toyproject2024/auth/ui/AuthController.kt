package com.minseoklim.toyproject2024.auth.ui

import com.minseoklim.toyproject2024.auth.application.TokenService
import com.minseoklim.toyproject2024.auth.dto.TokenRequest
import com.minseoklim.toyproject2024.auth.dto.TokenResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val tokenService: TokenService
) {
    @GetMapping("/validate-token")
    fun validateToken(): ResponseEntity<Unit> {
        return ResponseEntity.ok().build() // JwtFilter에서 검증되었기 때문에 200 OK 반환
    }

    @PostMapping("/refresh-token")
    fun refreshToken(@Valid @RequestBody request: TokenRequest): ResponseEntity<TokenResponse> {
        val response = tokenService.refreshToken(request)
        return ResponseEntity.ok(response)
    }
}
