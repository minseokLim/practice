package com.minseoklim.toyproject2024.member.ui

import com.minseoklim.toyproject2024.common.annotation.CheckAdminPermission
import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.member.application.DeleteMemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class DeleteMemberController(
    private val deleteMemberService: DeleteMemberService
) {
    @DeleteMapping("/{id}")
    @CheckAdminPermission
    fun delete(
        @PathVariable id: Long
    ): ResponseEntity<Unit> {
        deleteMemberService.delete(id)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/me")
    fun deleteMe(
        @MemberId id: Long
    ): ResponseEntity<Unit> {
        deleteMemberService.delete(id)
        return ResponseEntity.noContent().build()
    }
}
