package com.minseoklim.toyproject2024.chat.domain.repository

import com.minseoklim.toyproject2024.chat.domain.model.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ChatRoomRepository : JpaRepository<ChatRoom, Long> {
    @Query(
        """
            SELECT DISTINCT cr FROM ChatRoom cr
            JOIN FETCH cr.chatRoomMembers.values crm
            WHERE cr.id IN (
                SELECT cr2.id FROM ChatRoom cr2
                JOIN cr2.chatRoomMembers.values crm2
                WHERE crm2.memberId = :memberId
            )
            ORDER BY cr.modifiedDateTime DESC
        """
    )
    fun findAllByMemberId(memberId: Int): List<ChatRoom>
}
