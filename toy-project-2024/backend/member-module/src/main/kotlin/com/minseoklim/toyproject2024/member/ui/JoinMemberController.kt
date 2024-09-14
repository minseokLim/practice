package com.minseoklim.toyproject2024.member.ui

import com.minseoklim.toyproject2024.member.application.JoinMemberService
import com.minseoklim.toyproject2024.member.dto.ui.JoinMemberRequest
import com.minseoklim.toyproject2024.member.dto.ui.JoinMemberResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/members")
class JoinMemberController(
    private val joinMemberService: JoinMemberService
) {
    @PostMapping
    fun join(
        @Valid @RequestBody request: JoinMemberRequest
    ): ResponseEntity<JoinMemberResponse> {
        val output = joinMemberService.join(request.toInput())
        val uri = URI.create("/members/${output.id}")
        return ResponseEntity.created(uri).body(JoinMemberResponse.of(output))
    }
}
