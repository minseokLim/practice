package com.minseoklim.toyproject2024.feed.ui

import com.minseoklim.toyproject2024.feed.application.QueryFeedService
import com.minseoklim.toyproject2024.feed.dto.ui.QueryFeedResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/feeds")
class QueryFeedController(
    private val queryFeedService: QueryFeedService
) {
    @GetMapping
    fun list(
        @RequestParam(required = false, defaultValue = Long.MAX_VALUE.toString()) cursorId: Long,
        @RequestParam size: Int
    ): ResponseEntity<List<QueryFeedResponse>> {
        val outputs = queryFeedService.list(cursorId, size)
        return ResponseEntity.ok(outputs.map { QueryFeedResponse.from(it) })
    }
}
