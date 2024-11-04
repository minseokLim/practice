package com.minseoklim.toyproject2024.feed.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FeedTest {
    @Test
    fun update() {
        // given
        val feed = Feed(
            title = "title",
            content = "content",
            feedType = FeedType.TEXT
        )
        val other = Feed(
            title = "new title",
            content = "new content",
            feedType = FeedType.IMAGE
        )

        // when
        feed.update(other)

        // then
        assertThat(feed.title).isEqualTo("new title")
        assertThat(feed.content).isEqualTo("new content")
        assertThat(feed.feedType).isEqualTo(FeedType.IMAGE)
    }
}
