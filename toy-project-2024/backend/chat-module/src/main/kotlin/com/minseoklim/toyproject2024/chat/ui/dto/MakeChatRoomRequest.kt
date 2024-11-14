package com.minseoklim.toyproject2024.chat.ui.dto

import jakarta.validation.constraints.NotEmpty

data class MakeChatRoomRequest(
    @get:NotEmpty
    val memberIds: List<Long>
)
