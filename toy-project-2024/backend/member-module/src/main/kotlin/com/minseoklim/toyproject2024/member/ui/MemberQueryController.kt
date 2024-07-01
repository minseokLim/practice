package com.minseoklim.toyproject2024.member.ui

import com.minseoklim.toyproject2024.common.annotation.CheckAdminPermission
import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.member.application.MemberQueryService
import com.minseoklim.toyproject2024.member.dto.MemberResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberQueryController(
    private val memberQueryService: MemberQueryService
) {
    @GetMapping
    @CheckAdminPermission
    fun list(pageable: Pageable): ResponseEntity<Page<MemberResponse>> {
        val responses = memberQueryService.list(pageable)
        return ResponseEntity.ok(responses)
    }

    @GetMapping("/{id}")
    @CheckAdminPermission
    fun get(@PathVariable id: Int): ResponseEntity<MemberResponse> {
        val response = memberQueryService.get(id)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/me")
    fun getMe(@MemberId id: Int): ResponseEntity<MemberResponse> {
        val response = memberQueryService.get(id)
        return ResponseEntity.ok(response)
    }
}
