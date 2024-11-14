package com.minseoklim.toyproject2024.member.application.dto

import com.minseoklim.toyproject2024.member.domain.model.SocialType

data class AddSocialLinkInput(
    val socialType: SocialType,
    val socialId: String
)
