package com.minseoklim.toyproject2024.feed.dto.application

import com.minseoklim.toyproject2024.feed.domain.model.Feed
import com.minseoklim.toyproject2024.feed.domain.model.FeedAttachedFile
import com.minseoklim.toyproject2024.feed.domain.model.FeedType

data class RegisterFeedInput(
    val title: String,
    val content: String,
    val feedType: FeedType,
    val youtubeLinkUrl: String? = null
) {
    fun toEntity(
        memberId: Long,
        files: List<FeedAttachedFile>
    ): Feed {
        return Feed(
            title = title,
            content = content,
            feedType = feedType,
            files = files,
            youtubeLinkUrl = youtubeLinkUrl,
            memberId = memberId
        )
    }
}
