package com.minseoklim.toyproject2024.chat.ui

import com.minseoklim.toyproject2024.chat.application.QueryChatRoomService
import com.minseoklim.toyproject2024.chat.dto.ui.QueryChatRoomResponse
import com.minseoklim.toyproject2024.common.annotation.MemberId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryChatRoomController(
    private val queryChatRoomService: QueryChatRoomService
) {
    @GetMapping("/chat-rooms")
    fun list(
        @MemberId memberId: Long
    ): ResponseEntity<List<QueryChatRoomResponse>> {
        val outputs = queryChatRoomService.list(memberId)
        return ResponseEntity.ok(outputs.map { QueryChatRoomResponse.from(it) })
    }
}
