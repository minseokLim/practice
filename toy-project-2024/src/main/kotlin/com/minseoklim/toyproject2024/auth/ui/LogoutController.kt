package com.minseoklim.toyproject2024.auth.ui

import com.minseoklim.toyproject2024.auth.annotation.MemberId
import com.minseoklim.toyproject2024.auth.application.LogoutService
import com.minseoklim.toyproject2024.auth.dto.TokenRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LogoutController(
    private val logoutService: LogoutService
) {
    @PostMapping("/logout")
    fun logout(@MemberId memberId: Int, @RequestBody request: TokenRequest): ResponseEntity<Unit> {
        logoutService.logout(memberId, request)
        return ResponseEntity.ok().build()
    }
}
