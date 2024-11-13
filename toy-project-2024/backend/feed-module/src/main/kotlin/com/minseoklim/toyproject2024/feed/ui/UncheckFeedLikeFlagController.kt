package com.minseoklim.toyproject2024.feed.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.feed.application.UncheckFeedLikeFlagService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/feeds")
class UncheckFeedLikeFlagController(
    private val uncheckFeedLikeFlagService: UncheckFeedLikeFlagService
) {
    @DeleteMapping("{id}/likes")
    fun uncheck(
        @MemberId memberId: Long,
        @PathVariable id: Long
    ): ResponseEntity<Unit> {
        uncheckFeedLikeFlagService.uncheck(memberId = memberId, feedId = id)
        return ResponseEntity.ok().build()
    }
}
