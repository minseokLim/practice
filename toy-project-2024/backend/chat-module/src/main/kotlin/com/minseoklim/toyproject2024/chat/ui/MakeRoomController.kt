package com.minseoklim.toyproject2024.chat.ui

import com.minseoklim.toyproject2024.chat.application.MakeRoomService
import com.minseoklim.toyproject2024.chat.dto.ui.MakeRoomRequest
import com.minseoklim.toyproject2024.chat.dto.ui.MakeRoomResponse
import com.minseoklim.toyproject2024.common.annotation.MemberId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class MakeRoomController(
    private val makeRoomService: MakeRoomService
) {
    @PostMapping("/chat-rooms")
    fun make(
        @MemberId creatorId: Int,
        @Valid @RequestBody request: MakeRoomRequest
    ): ResponseEntity<MakeRoomResponse> {
        val output = makeRoomService.make(creatorId, request.memberIds)
        val uri = URI.create("/chat-rooms/${output.id}")
        return ResponseEntity.created(uri).body(MakeRoomResponse.from(output))
    }
}
