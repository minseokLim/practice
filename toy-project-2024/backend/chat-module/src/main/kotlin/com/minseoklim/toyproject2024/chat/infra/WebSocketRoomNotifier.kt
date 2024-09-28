package com.minseoklim.toyproject2024.chat.infra

import com.minseoklim.toyproject2024.chat.application.RoomNotifier
import com.minseoklim.toyproject2024.chat.dto.application.RoomDto
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class WebSocketRoomNotifier(
    private val messagingTemplate: SimpMessagingTemplate
) : RoomNotifier {
    @Async
    override fun notify(
        memberIds: Collection<Int>,
        room: RoomDto
    ) {
        memberIds.forEach {
            messagingTemplate.convertAndSendToUser(
                it.toString(),
                "/topic/notify-room",
                room
            )
        }
    }
}
