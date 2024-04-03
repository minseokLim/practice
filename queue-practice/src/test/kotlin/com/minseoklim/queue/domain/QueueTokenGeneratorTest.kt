package com.minseoklim.queue.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class QueueTokenGeneratorTest {

    @Test
    fun generate() {
        // when
        val token = QueueTokenGenerator.generate()

        // then
        assertThat(token).isNotBlank()
    }
}
