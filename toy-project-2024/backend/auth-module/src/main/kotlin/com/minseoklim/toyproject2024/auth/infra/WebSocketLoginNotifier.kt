package com.minseoklim.toyproject2024.auth.infra

import com.minseoklim.toyproject2024.auth.application.LoginNotifier
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class WebSocketLoginNotifier(
    private val messagingTemplate: SimpMessagingTemplate
) : LoginNotifier {
    override fun notifyLogin(
        memberId: Int,
        clientIp: String,
        userAgent: String,
        loginDateTime: LocalDateTime
    ) {
        messagingTemplate.convertAndSendToUser(
            memberId.toString(),
            "/topic/notify-login",
            LoginNotification(clientIp, userAgent, loginDateTime)
        )
    }

    private data class LoginNotification(
        val clientIp: String,
        val userAgent: String,
        val loginDateTime: LocalDateTime
    )
}
