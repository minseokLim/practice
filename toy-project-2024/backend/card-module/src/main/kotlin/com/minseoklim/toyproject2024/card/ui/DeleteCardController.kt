package com.minseoklim.toyproject2024.card.ui

import com.minseoklim.toyproject2024.card.application.DeleteCardService
import com.minseoklim.toyproject2024.common.annotation.MemberId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cards")
class DeleteCardController(
    private val deleteCardService: DeleteCardService
) {
    @DeleteMapping("/{cardId}")
    fun delete(
        @MemberId memberId: Int,
        @PathVariable cardId: Int
    ): ResponseEntity<Unit> {
        deleteCardService.delete(memberId, cardId)
        return ResponseEntity.noContent().build()
    }
}
