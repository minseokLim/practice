package com.minseoklim.toyproject2024.member.ui

import com.minseoklim.toyproject2024.common.annotation.CheckAdminPermission
import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.member.application.QueryMemberService
import com.minseoklim.toyproject2024.member.dto.ui.QueryMemberResponse
import com.minseoklim.toyproject2024.member.dto.ui.SimpleQueryMemberResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class QueryMemberController(
    private val queryMemberService: QueryMemberService
) {
    @GetMapping
    @CheckAdminPermission
    fun list(
        @RequestParam(required = false) filter: String?,
        pageable: Pageable
    ): ResponseEntity<Page<QueryMemberResponse>> {
        val outputs = queryMemberService.list(filter, pageable)
        return ResponseEntity.ok(outputs.map { QueryMemberResponse.from(it) })
    }

    @GetMapping("/{id}")
    @CheckAdminPermission
    fun get(
        @PathVariable id: Int
    ): ResponseEntity<QueryMemberResponse> {
        val output = queryMemberService.get(id)
        return ResponseEntity.ok(QueryMemberResponse.from(output))
    }

    @GetMapping("/me")
    fun getMe(
        @MemberId id: Int
    ): ResponseEntity<QueryMemberResponse> {
        val output = queryMemberService.get(id)
        return ResponseEntity.ok(QueryMemberResponse.from(output))
    }

    @GetMapping("/except-me")
    fun listExceptMe(
        @MemberId id: Int
    ): ResponseEntity<List<SimpleQueryMemberResponse>> {
        val outputs = queryMemberService.findAllNotDeletedExceptId(id)
        return ResponseEntity.ok(outputs.map { SimpleQueryMemberResponse.from(it) })
    }
}
