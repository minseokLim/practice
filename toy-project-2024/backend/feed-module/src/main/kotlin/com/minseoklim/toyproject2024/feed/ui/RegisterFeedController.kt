package com.minseoklim.toyproject2024.feed.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.feed.application.RegisterFeedService
import com.minseoklim.toyproject2024.feed.dto.ui.RegisterFeedRequest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/feeds")
class RegisterFeedController(
    private val registerFeedService: RegisterFeedService
) {
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun register(
        @MemberId memberId: Long,
        @ModelAttribute request: RegisterFeedRequest
    ): ResponseEntity<Unit> {
        registerFeedService.register(memberId, request.toInput(), request.files)
        return ResponseEntity.ok().build()
    }
}
