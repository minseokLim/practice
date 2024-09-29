package com.minseoklim.toyproject2024.chat.dto.ui

import jakarta.validation.constraints.NotEmpty

data class MakeChatRoomRequest(
    @get:NotEmpty
    val memberIds: List<Int>
)
