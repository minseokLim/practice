package com.minseoklim.toyproject2024.member.ui

import com.minseoklim.toyproject2024.member.application.MemberService
import com.minseoklim.toyproject2024.member.dto.MemberJoinRequest
import com.minseoklim.toyproject2024.member.dto.MemberResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping
    fun join(@RequestBody request: MemberJoinRequest): ResponseEntity<MemberResponse> {
        val response = memberService.join(request)
        val uri = URI.create("/members/${response.id}")
        return ResponseEntity.created(uri).body(response)
    }
}
