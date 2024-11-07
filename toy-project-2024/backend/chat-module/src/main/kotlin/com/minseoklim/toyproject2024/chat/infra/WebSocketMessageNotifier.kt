package com.minseoklim.toyproject2024.chat.infra

import com.minseoklim.toyproject2024.chat.application.MessageNotifier
import com.minseoklim.toyproject2024.chat.dto.application.MessageDto
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class WebSocketMessageNotifier(
    private val messagingTemplate: SimpMessagingTemplate
) : MessageNotifier {
    @Async
    override fun notify(
        memberIds: Collection<Long>,
        message: MessageDto
    ) {
        memberIds.forEach {
            messagingTemplate.convertAndSendToUser(
                it.toString(),
                "/topic/notify-message",
                message
            )
        }
    }
}
