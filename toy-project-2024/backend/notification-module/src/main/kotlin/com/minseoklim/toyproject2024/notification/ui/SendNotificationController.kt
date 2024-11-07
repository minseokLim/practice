package com.minseoklim.toyproject2024.notification.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.notification.application.SendNotificationService
import com.minseoklim.toyproject2024.notification.dto.ui.SendNotificationRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SendNotificationController(
    private val sendNotificationService: SendNotificationService
) {
    @PostMapping("/notifications")
    fun send(
        @MemberId memberId: Long,
        @Valid @RequestBody request: SendNotificationRequest
    ): ResponseEntity<Unit> {
        sendNotificationService.send(memberId, request.toInput())
        return ResponseEntity.ok().build()
    }
}
