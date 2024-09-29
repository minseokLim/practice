package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.dto.application.ChatRoomDto

interface ChatRoomNotifier {
    fun notify(
        memberIds: Collection<Int>,
        chatRoom: ChatRoomDto
    )
}
