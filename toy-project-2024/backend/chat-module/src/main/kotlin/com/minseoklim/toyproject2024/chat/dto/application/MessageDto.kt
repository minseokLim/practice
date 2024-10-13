package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.Message
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import java.time.LocalDateTime

data class MessageDto private constructor(
    val id: Long,
    val content: String,
    val chatRoomId: Long,
    val member: MemberDto,
    val isDeleted: Boolean,
    val createdDateTime: LocalDateTime
) {
    companion object {
        fun of(
            message: Message,
            memberIdToName: Map<Int, String>
        ): MessageDto {
            return MessageDto(
                id = checkNotNull(message.id),
                content = TextEncryptUtil.decrypt(message.encryptedContent),
                chatRoomId = message.chatRoomId,
                member = MemberDto(message.memberId, memberIdToName.getValue(message.memberId)),
                isDeleted = message.isDeleted,
                createdDateTime = message.createdDateTime
            )
        }
    }

    data class MemberDto(
        val id: Int,
        val name: String
    )
}
