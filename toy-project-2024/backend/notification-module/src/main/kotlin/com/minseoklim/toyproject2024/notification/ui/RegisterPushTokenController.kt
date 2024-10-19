package com.minseoklim.toyproject2024.notification.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.notification.application.RegisterPushTokenService
import com.minseoklim.toyproject2024.notification.dto.ui.RegisterPushTokenRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterPushTokenController(
    private val registerPushTokenService: RegisterPushTokenService
) {
    @PostMapping("/push-tokens")
    fun register(
        @MemberId memberId: Int,
        @Valid @RequestBody request: RegisterPushTokenRequest
    ): ResponseEntity<Unit> {
        registerPushTokenService.register(memberId, request.token)
        return ResponseEntity.ok().build()
    }
}
