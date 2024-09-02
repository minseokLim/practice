package com.minseoklim.toyproject2024.card.ui

import com.minseoklim.toyproject2024.card.application.QueryCardService
import com.minseoklim.toyproject2024.card.dto.ui.QueryCardResponse
import com.minseoklim.toyproject2024.common.annotation.MemberId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cards")
class QueryCardController(
    private val queryCardService: QueryCardService
) {
    @GetMapping
    fun list(@MemberId memberId: Int, pageable: Pageable): ResponseEntity<Page<QueryCardResponse>> {
        val outputs = queryCardService.list(memberId, pageable)
        return ResponseEntity.ok(outputs.map { QueryCardResponse.of(it) })
    }
}
