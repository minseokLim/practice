package com.minseoklim.toyproject2024.auth.domain

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AccessTokenTest {

    @Test
    fun delete() {
        // given
        val accessToken = AccessToken(
            id = "id",
            memberId = 1,
            content = "content"
        )

        // when
        accessToken.delete()

        // then
        assertThat(accessToken.isDeleted).isTrue
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val accessToken1 = AccessToken(
            id = "id",
            memberId = 1,
            content = "content"
        )
        val accessToken2 = AccessToken(
            id = "id",
            memberId = 2,
            content = "other content"
        )
        val accessToken3 = AccessToken(
            id = "other id",
            memberId = 3,
            content = "other content"
        )

        // when, then
        TestUtil.testEqualsAndHashCode(accessToken1, accessToken2, accessToken3)
    }
}
