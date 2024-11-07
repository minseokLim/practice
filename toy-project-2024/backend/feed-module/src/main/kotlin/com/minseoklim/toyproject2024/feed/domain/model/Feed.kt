package com.minseoklim.toyproject2024.feed.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(columnList = "member_id")])
class Feed(
    title: String,
    content: String,
    feedType: FeedType,
    files: List<FeedAttachedFile>,
    youtubeLinkUrl: String? = null,
    memberId: Long
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var title: String = title
        protected set

    var content: String = content
        protected set

    @Enumerated(EnumType.STRING)
    var feedType: FeedType = feedType
        protected set

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "feed_id")
    val files: MutableList<FeedAttachedFile> = files.toMutableList()

    var youtubeLinkUrl: String? = youtubeLinkUrl
        protected set

    val memberId: Long = memberId

    init {
        validate()
    }

    private fun validate() {
        when (feedType) {
            FeedType.TEXT -> {
                if (files.isNotEmpty() || youtubeLinkUrl != null) {
                    throw BadRequestException(ErrorCode.INVALID_FEED_FIELD)
                }
            }

            FeedType.YOUTUBE -> {
                if (files.isNotEmpty() || youtubeLinkUrl == null) {
                    throw BadRequestException(ErrorCode.INVALID_FEED_FIELD)
                }
            }

            FeedType.IMAGE, FeedType.VIDEO, FeedType.AUDIO, FeedType.PDF, FeedType.DOC -> {
                if (files.isEmpty() || youtubeLinkUrl != null) {
                    throw BadRequestException(ErrorCode.INVALID_FEED_FIELD)
                }
            }
        }
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}
