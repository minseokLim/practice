package com.minseoklim.toyproject2024.member.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.member.application.DeleteSocialLinkService
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class DeleteSocialLinkController(
    private val deleteSocialLinkService: DeleteSocialLinkService
) {
    @DeleteMapping("/me/social-links/{socialType}")
    fun deleteSocialLink(
        @MemberId id: Int,
        @PathVariable socialType: SocialType
    ): ResponseEntity<Unit> {
        deleteSocialLinkService.deleteSocialLink(id, socialType)
        return ResponseEntity.noContent().build()
    }
}
