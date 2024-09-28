package com.minseoklim.toyproject2024.chat.application

import com.minseoklim.toyproject2024.chat.dto.application.RoomDto

interface RoomNotifier {
    fun notify(
        memberIds: Collection<Int>,
        room: RoomDto
    )
}
