package com.minseoklim.toyproject2024.chat.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(columnList = "chat_room_id")])
class Message(
    content: String,
    chatRoomId: Long,
    memberId: Long,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val encryptedContent: String = TextEncryptUtil.encrypt(content)

    val chatRoomId: Long = chatRoomId

    val memberId: Long = memberId

    var isDeleted: Boolean = false
        protected set

    fun checkAuthority(memberId: Long) {
        if (this.memberId != memberId) {
            throw NoPermissionException(ErrorCode.NO_MESSAGE_PERMISSION)
        }
    }

    fun delete() {
        isDeleted = true
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}
