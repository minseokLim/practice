package com.minseoklim.toyproject2024.feed.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.feed.application.CheckFeedLikeFlagService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/feeds")
class CheckFeedLikeFlagController(
    private val checkFeedLikeFlagService: CheckFeedLikeFlagService
) {
    @PostMapping("{id}/likes")
    fun check(
        @MemberId memberId: Long,
        @PathVariable id: Long
    ): ResponseEntity<Unit> {
        checkFeedLikeFlagService.check(memberId = memberId, feedId = id)
        return ResponseEntity.ok().build()
    }
}
