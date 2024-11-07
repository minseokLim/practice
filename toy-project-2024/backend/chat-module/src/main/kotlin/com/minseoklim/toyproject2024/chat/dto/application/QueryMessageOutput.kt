package com.minseoklim.toyproject2024.chat.dto.application

import com.minseoklim.toyproject2024.chat.domain.model.Message
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import java.time.LocalDateTime

data class QueryMessageOutput private constructor(
    val id: Long,
    val content: String,
    val chatRoomId: Long,
    val member: MemberOutput,
    val isDeleted: Boolean,
    val createdDateTime: LocalDateTime
) {
    companion object {
        fun of(
            message: Message,
            memberIdToName: Map<Long, String>
        ): QueryMessageOutput {
            return QueryMessageOutput(
                id = checkNotNull(message.id),
                content = TextEncryptUtil.decrypt(message.encryptedContent),
                chatRoomId = message.chatRoomId,
                member = MemberOutput(message.memberId, memberIdToName.getValue(message.memberId)),
                isDeleted = message.isDeleted,
                createdDateTime = message.createdDateTime
            )
        }
    }

    data class MemberOutput(
        val id: Long,
        val name: String
    )
}
