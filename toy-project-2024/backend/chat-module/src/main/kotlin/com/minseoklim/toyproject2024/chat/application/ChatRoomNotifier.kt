package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.application.dto.ChatRoomDto

interface ChatRoomNotifier {
    fun notify(
        memberIds: Collection<Long>,
        chatRoom: ChatRoomDto
    )
}
