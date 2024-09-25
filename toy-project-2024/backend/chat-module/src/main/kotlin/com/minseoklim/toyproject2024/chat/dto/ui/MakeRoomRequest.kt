package com.minseoklim.toyproject2024.chat.dto.ui

import jakarta.validation.constraints.NotEmpty

data class MakeRoomRequest(
    @get:NotEmpty
    val memberIds: List<Int>
)
