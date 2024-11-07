package com.minseoklim.toyproject2024.member.ui

import com.minseoklim.toyproject2024.common.annotation.CheckAdminPermission
import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.member.application.UpdateMemberService
import com.minseoklim.toyproject2024.member.dto.ui.UpdateMemberRequest
import com.minseoklim.toyproject2024.member.dto.ui.UpdateMemberResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class UpdateMemberController(
    private val updateMemberService: UpdateMemberService
) {
    @PutMapping("/{id}")
    @CheckAdminPermission
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateMemberRequest
    ): ResponseEntity<UpdateMemberResponse> {
        val output = updateMemberService.update(id, request.toInput())
        return ResponseEntity.ok(UpdateMemberResponse.from(output))
    }

    @PutMapping("/me")
    fun updateMe(
        @MemberId id: Long,
        @Valid @RequestBody request: UpdateMemberRequest
    ): ResponseEntity<UpdateMemberResponse> {
        val output = updateMemberService.update(id, request.toInput())
        return ResponseEntity.ok(UpdateMemberResponse.from(output))
    }
}
