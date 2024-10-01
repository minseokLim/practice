package com.minseoklim.toyproject2024.chat.dto.application

data class UpdateLastReadMessageInput(
    val chatRoomId: Long,
    val memberId: Int,
    val lastReadMessageId: Long
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "chatRoomId" to chatRoomId,
            "memberId" to memberId,
            "lastReadMessageId" to lastReadMessageId
        )
    }
}
