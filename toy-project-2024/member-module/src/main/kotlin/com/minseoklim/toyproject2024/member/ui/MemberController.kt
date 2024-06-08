package com.minseoklim.toyproject2024.member.ui

import com.minseoklim.toyproject2024.auth.annotation.CheckAdminPermission
import com.minseoklim.toyproject2024.auth.annotation.MemberId
import com.minseoklim.toyproject2024.member.application.MemberService
import com.minseoklim.toyproject2024.member.dto.MemberJoinRequest
import com.minseoklim.toyproject2024.member.dto.MemberResponse
import com.minseoklim.toyproject2024.member.dto.MemberUpdateRequest
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
    fun join(@Valid @RequestBody request: MemberJoinRequest): ResponseEntity<MemberResponse> {
        val response = memberService.join(request)
        val uri = URI.create("/members/${response.id}")
        return ResponseEntity.created(uri).body(response)
    }

    @GetMapping
    @CheckAdminPermission
    fun list(pageable: Pageable): ResponseEntity<Page<MemberResponse>> {
        val responses = memberService.list(pageable)
        return ResponseEntity.ok(responses)
    }

    @GetMapping("/{id}")
    @CheckAdminPermission
    fun get(@PathVariable id: Int): ResponseEntity<MemberResponse> {
        val response = memberService.get(id)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    @CheckAdminPermission
    fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        memberService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/me")
    fun getMe(@MemberId id: Int): ResponseEntity<MemberResponse> {
        val response = memberService.get(id)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/me")
    fun updateMe(@MemberId id: Int, @Valid @RequestBody request: MemberUpdateRequest): ResponseEntity<MemberResponse> {
        val response = memberService.update(id, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/me")
    fun deleteMe(@MemberId id: Int): ResponseEntity<Unit> {
        memberService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
