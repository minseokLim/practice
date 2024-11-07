package com.minseoklim.toyproject2024.member.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.member.application.AddSocialLinkService
import com.minseoklim.toyproject2024.member.dto.ui.AddSocialLinkRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class AddSocialLinkController(
    private val addSocialLinkService: AddSocialLinkService
) {
    @PostMapping("/me/social-links")
    fun addSocialLink(
        @MemberId id: Long,
        @Valid @RequestBody request: AddSocialLinkRequest
    ): ResponseEntity<Unit> {
        addSocialLinkService.addSocialLink(id, request.toInput())
        return ResponseEntity.ok().build()
    }
}
