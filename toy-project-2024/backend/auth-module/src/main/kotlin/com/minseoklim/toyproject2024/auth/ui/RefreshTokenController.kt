package com.minseoklim.toyproject2024.auth.ui

import com.minseoklim.toyproject2024.auth.application.RefreshTokenService
import com.minseoklim.toyproject2024.auth.dto.ui.RefreshTokenRequest
import com.minseoklim.toyproject2024.auth.dto.ui.RefreshTokenResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RefreshTokenController(
    private val refreshTokenService: RefreshTokenService
) {
    @PostMapping("/refresh-token")
    fun refreshToken(@Valid @RequestBody request: RefreshTokenRequest): ResponseEntity<RefreshTokenResponse> {
        val output = refreshTokenService.refreshToken(request.toInput())
        return ResponseEntity.ok(RefreshTokenResponse.of(output))
    }
}
