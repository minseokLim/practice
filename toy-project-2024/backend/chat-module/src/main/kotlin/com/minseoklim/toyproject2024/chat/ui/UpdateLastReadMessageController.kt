package com.minseoklim.toyproject2024.chat.ui

import com.minseoklim.toyproject2024.chat.application.UpdateLastReadMessageService
import com.minseoklim.toyproject2024.chat.dto.ui.UpdateLastReadMessageRequest
import com.minseoklim.toyproject2024.common.annotation.MemberId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateLastReadMessageController(
    private val updateLastReadMessageService: UpdateLastReadMessageService
) {
    @PostMapping("/chat-rooms/{chatRoomId}/last-read-message")
    fun update(
        @MemberId memberId: Int,
        @PathVariable chatRoomId: Long,
        @Valid @RequestBody request: UpdateLastReadMessageRequest
    ): ResponseEntity<Unit> {
        updateLastReadMessageService.update(request.toInput(memberId, chatRoomId))
        return ResponseEntity.ok().build()
    }
}
