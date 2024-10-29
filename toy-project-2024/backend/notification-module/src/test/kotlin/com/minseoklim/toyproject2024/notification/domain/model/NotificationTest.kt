package com.minseoklim.toyproject2024.notification.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class NotificationTest {
    @Test
    fun equalsAndHashCode() {
        // given
        val notification1 = Notification(
            title = "title",
            body = "body",
            imageUrl = "imageUrl",
            memberId = 1
        )
        val notification2 = Notification(
            title = "title",
            body = "body",
            imageUrl = "imageUrl",
            memberId = 1
        )
        val notification3 = Notification(
            title = "title",
            body = "body",
            imageUrl = "imageUrl",
            memberId = 2
        )
        ReflectionTestUtils.setField(notification1, "id", 1)
        ReflectionTestUtils.setField(notification2, "id", 1)

        // when, then
        assertThat(setOf(notification1, notification2, notification3)).hasSize(2)
    }
}
