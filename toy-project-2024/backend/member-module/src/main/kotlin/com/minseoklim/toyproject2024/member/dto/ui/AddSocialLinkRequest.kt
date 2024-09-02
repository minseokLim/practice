package com.minseoklim.toyproject2024.member.dto.ui

import com.minseoklim.toyproject2024.member.domain.model.SocialId
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import com.minseoklim.toyproject2024.member.dto.application.AddSocialLinkInput
import jakarta.validation.constraints.NotBlank

data class AddSocialLinkRequest(
    val socialType: SocialType,

    @get:NotBlank(message = SocialId.ERR_MSG)
    val socialId: String
) {
    fun toInput(): AddSocialLinkInput {
        return AddSocialLinkInput(
            socialType = socialType,
            socialId = socialId
        )
    }
}
