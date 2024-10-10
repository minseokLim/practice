package com.minseoklim.toyproject2024.chat.ui

import com.minseoklim.toyproject2024.chat.application.QueryMessageService
import com.minseoklim.toyproject2024.chat.dto.ui.QueryMessageResponse
import com.minseoklim.toyproject2024.common.annotation.MemberId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryMessageController(
    private val queryMessageService: QueryMessageService
) {
    @GetMapping("/messages")
    fun list(
        @MemberId memberId: Int,
        @RequestParam chatRoomId: Long,
        @RequestParam(required = false, defaultValue = Long.MAX_VALUE.toString()) cursorId: Long,
        @RequestParam size: Int
    ): ResponseEntity<List<QueryMessageResponse>> {
        val outputs = queryMessageService.list(
            memberId = memberId,
            chatRoomId = chatRoomId,
            cursorId = cursorId,
            size = size
        )
        return ResponseEntity.ok(outputs.map { QueryMessageResponse.from(it) })
    }
}
