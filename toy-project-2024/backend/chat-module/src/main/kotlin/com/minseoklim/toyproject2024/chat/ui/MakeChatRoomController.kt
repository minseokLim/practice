package com.minseoklim.toyproject2024.chat.ui

import com.minseoklim.toyproject2024.chat.application.MakeChatRoomService
import com.minseoklim.toyproject2024.chat.dto.ui.MakeChatRoomRequest
import com.minseoklim.toyproject2024.chat.dto.ui.MakeChatRoomResponse
import com.minseoklim.toyproject2024.common.annotation.MemberId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class MakeChatRoomController(
    private val makeChatRoomService: MakeChatRoomService
) {
    @PostMapping("/chat-rooms")
    fun make(
        @MemberId creatorId: Long,
        @Valid @RequestBody request: MakeChatRoomRequest
    ): ResponseEntity<MakeChatRoomResponse> {
        val output = makeChatRoomService.make(creatorId, request.memberIds)
        val uri = URI.create("/chat-rooms/${output.id}")
        return ResponseEntity.created(uri).body(MakeChatRoomResponse.from(output))
    }
}
