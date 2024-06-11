package com.minseoklim.toyproject2024.auth.ui

import com.minseoklim.toyproject2024.auth.annotation.MemberId
import com.minseoklim.toyproject2024.auth.application.LogoutService
import com.minseoklim.toyproject2024.auth.dto.TokenRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LogoutController(
    private val logoutService: LogoutService
) {
    @PostMapping("/logout")
    fun logout(@MemberId memberId: Int, @Valid @RequestBody request: TokenRequest): ResponseEntity<Unit> {
        logoutService.logout(memberId, request)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/logout-all")
    fun logoutAll(@MemberId memberId: Int): ResponseEntity<Unit> {
        logoutService.logoutAll(memberId)
        return ResponseEntity.ok().build()
    }
}
