package com.minseoklim.toyproject2024.member.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.member.application.SocialLinkService
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import com.minseoklim.toyproject2024.member.dto.SocialLinkCreateRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class SocialLinkController(
    private val socialLinkService: SocialLinkService
) {
    @PostMapping("/me/social-links")
    fun addSocialLink(@MemberId id: Int, @Valid @RequestBody request: SocialLinkCreateRequest): ResponseEntity<Unit> {
        socialLinkService.addSocialLink(id, request)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/me/social-links/{socialType}")
    fun deleteSocialLink(@MemberId id: Int, @PathVariable socialType: SocialType): ResponseEntity<Unit> {
        socialLinkService.deleteSocialLink(id, socialType)
        return ResponseEntity.noContent().build()
    }
}
