package com.minseoklim.toyproject2024.auth.domain

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
        val set = hashSetOf<AccessToken>()

        // when
        set.add(accessToken1)
        set.add(accessToken2)

        // then
        assertThat(set.size).isEqualTo(1)
    }
}
