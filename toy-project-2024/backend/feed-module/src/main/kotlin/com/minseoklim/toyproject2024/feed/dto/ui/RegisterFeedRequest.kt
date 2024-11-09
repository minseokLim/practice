package com.minseoklim.toyproject2024.feed.dto.ui

import com.minseoklim.toyproject2024.feed.domain.model.FeedType
import com.minseoklim.toyproject2024.feed.dto.application.RegisterFeedInput
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
