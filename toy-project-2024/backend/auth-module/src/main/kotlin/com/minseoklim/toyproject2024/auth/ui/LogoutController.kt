package com.minseoklim.toyproject2024.auth.ui

import com.minseoklim.toyproject2024.auth.application.LogoutService
import com.minseoklim.toyproject2024.auth.dto.ui.LogoutRequest
import com.minseoklim.toyproject2024.common.annotation.MemberId
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
    fun logout(
        @Valid @RequestBody request: LogoutRequest
    ): ResponseEntity<Unit> {
        logoutService.logout(request.toInput())
        return ResponseEntity.ok().build()
    }

    @PostMapping("/logout-all")
    fun logoutAll(
        @MemberId memberId: Int
    ): ResponseEntity<Unit> {
        logoutService.logoutAll(memberId)
        return ResponseEntity.ok().build()
    }
}
