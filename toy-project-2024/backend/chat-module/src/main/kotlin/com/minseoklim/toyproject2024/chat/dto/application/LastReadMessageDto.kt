package com.minseoklim.toyproject2024.chat.dto.application

data class LastReadMessageDto(
    val chatRoomId: Long,
    val memberId: Int,
    val lastReadMessageId: Long
)