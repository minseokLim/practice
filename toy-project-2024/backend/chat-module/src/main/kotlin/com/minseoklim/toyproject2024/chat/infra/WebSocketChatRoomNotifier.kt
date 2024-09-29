package com.minseoklim.toyproject2024.chat.infra

import com.minseoklim.toyproject2024.chat.application.ChatRoomNotifier
import com.minseoklim.toyproject2024.chat.dto.application.ChatRoomDto
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class WebSocketChatRoomNotifier(
    private val messagingTemplate: SimpMessagingTemplate
) : ChatRoomNotifier {
    @Async
    override fun notify(
        memberIds: Collection<Int>,
        chatRoom: ChatRoomDto
    ) {
        memberIds.forEach {
            messagingTemplate.convertAndSendToUser(
                it.toString(),
                "/topic/notify-chat-room",
                chatRoom
            )
        }
    }
}
