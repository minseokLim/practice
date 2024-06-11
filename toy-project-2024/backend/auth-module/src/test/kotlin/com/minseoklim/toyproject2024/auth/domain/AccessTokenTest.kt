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
}
