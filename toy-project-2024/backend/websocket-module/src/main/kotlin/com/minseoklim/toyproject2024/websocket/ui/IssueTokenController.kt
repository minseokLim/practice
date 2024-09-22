package com.minseoklim.toyproject2024.websocket.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.websocket.application.IssueTokenService
import com.minseoklim.toyproject2024.websocket.dto.ui.IssueTokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IssueTokenController(
    private val issueTokenService: IssueTokenService
) {
    @PostMapping("/websocket/token")
    fun issue(
        @MemberId memberId: Int
    ): ResponseEntity<IssueTokenResponse> {
        val token = issueTokenService.issue(memberId)
        return ResponseEntity.ok(IssueTokenResponse(token))
    }
}
