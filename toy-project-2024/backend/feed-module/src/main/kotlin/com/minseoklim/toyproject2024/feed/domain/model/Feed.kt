package com.minseoklim.toyproject2024.feed.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Feed(
    title: String,
    content: String,
    feedType: FeedType,
    linkUrl: String? = null
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    var title: String = title
        protected set

    var content: String = content
        protected set

    var feedType: FeedType = feedType
        protected set

    var linkUrl: String? = linkUrl
        protected set

    fun update(other: Feed) {
        this.title = other.title
        this.content = other.content
        this.feedType = other.feedType
        this.linkUrl = other.linkUrl
    }
}
