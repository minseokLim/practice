package com.minseoklim.toyproject2024.auth.domain

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RefreshTokenTest {

    @Test
    fun delete() {
        // given
        val refreshToken = RefreshToken(
            id = "id",
            memberId = 1,
            content = "content"
        )

        // when
        refreshToken.delete()

        // then
        assertThat(refreshToken.isDeleted).isTrue
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val refreshToken1 = RefreshToken(
            id = "id",
            memberId = 1,
            content = "content"
        )
        val refreshToken2 = RefreshToken(
            id = "id",
            memberId = 2,
            content = "other content"
        )
        val refreshToken3 = RefreshToken(
            id = "other id",
            memberId = 3,
            content = "other content"
        )

        // when, then
        TestUtil.testEqualsAndHashCode(refreshToken1, refreshToken2, refreshToken3)
    }
}
