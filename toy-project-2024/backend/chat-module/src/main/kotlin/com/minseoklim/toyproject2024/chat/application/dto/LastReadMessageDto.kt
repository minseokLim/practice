package com.minseoklim.toyproject2024.chat.application.dto

data class LastReadMessageDto(
    val chatRoomId: Long,
    val memberId: Long,
    val lastReadMessageId: Long
)
