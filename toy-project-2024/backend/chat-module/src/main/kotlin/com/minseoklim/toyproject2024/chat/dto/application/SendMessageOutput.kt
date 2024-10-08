package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.Message
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil

data class SendMessageOutput private constructor(
    val id: Long,
    val content: String,
    val chatRoomId: Long,
    val memberId: Int,
    val isDeleted: Boolean
) {
    companion object {
        fun from(message: Message): SendMessageOutput {
            return SendMessageOutput(
                id = checkNotNull(message.id),
                content = TextEncryptUtil.decrypt(message.encryptedContent),
                chatRoomId = message.chatRoomId,
                memberId = message.memberId,
                isDeleted = message.isDeleted
            )
        }
    }
}
