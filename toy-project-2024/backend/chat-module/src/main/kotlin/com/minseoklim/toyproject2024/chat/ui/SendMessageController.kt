package com.minseoklim.toyproject2024.chat.ui

import com.minseoklim.toyproject2024.chat.application.SendMessageService
import com.minseoklim.toyproject2024.chat.dto.ui.SendMessageRequest
import com.minseoklim.toyproject2024.chat.dto.ui.SendMessageResponse
import jakarta.validation.Valid
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class SendMessageController(
    private val sendMessageService: SendMessageService
) {
    @MessageMapping("/send-message")
    fun send(
        principal: Principal,
        @Valid request: SendMessageRequest
    ): SendMessageResponse {
        val output = sendMessageService.send(principal.name.toInt(), request.toInput())
        return SendMessageResponse.from(output)
    }
}
