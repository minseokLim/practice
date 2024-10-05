package com.minseoklim.toyproject2024.chat.infra

import com.minseoklim.toyproject2024.chat.application.LastReadMessageNotifier
import com.minseoklim.toyproject2024.chat.dto.application.LastReadMessageDto
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class WebSocketLastReadMessageNotifier(
    private val messagingTemplate: SimpMessagingTemplate
) : LastReadMessageNotifier {
    override fun notify(
        memberIds: Collection<Int>,
        lastReadMessage: LastReadMessageDto
    ) {
        memberIds.forEach {
            messagingTemplate.convertAndSendToUser(
                it.toString(),
                "/topic/notify-last-read-message",
                lastReadMessage
            )
        }
    }
}
