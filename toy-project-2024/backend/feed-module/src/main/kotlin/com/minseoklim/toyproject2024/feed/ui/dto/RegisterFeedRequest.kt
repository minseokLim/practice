package com.minseoklim.toyproject2024.feed.ui.dto

import com.minseoklim.toyproject2024.feed.application.dto.RegisterFeedInput
import com.minseoklim.toyproject2024.feed.domain.model.FeedType
import org.springframework.web.multipart.MultipartFile

data class RegisterFeedRequest(
    val title: String,
    val content: String,
    val feedType: FeedType,
    val files: List<MultipartFile>,
    val youtubeLinkUrl: String? = null
) {
    fun toInput(): RegisterFeedInput {
        return RegisterFeedInput(
            title = title,
            content = content,
            feedType = feedType,
            youtubeLinkUrl = youtubeLinkUrl
        )
    }
}
