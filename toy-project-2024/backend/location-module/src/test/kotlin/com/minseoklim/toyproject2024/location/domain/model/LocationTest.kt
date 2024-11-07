package com.minseoklim.toyproject2024.location.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class LocationTest {
    @Test
    fun setCoordinate() {
        // given
        val location = Location(
            latitude = 1.0,
            longitude = 2.0,
            memberId = 1L
        )

        // when
        location.setCoordinate(latitude = 3.0, longitude = 4.0)

        // then
        assertThat(location.coordinate.x).isEqualTo(4.0)
        assertThat(location.coordinate.y).isEqualTo(3.0)
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val location1 = Location(
            latitude = 1.0,
            longitude = 2.0,
            memberId = 1L
        )
        val location2 = Location(
            latitude = 1.0,
            longitude = 2.0,
            memberId = 1L
        )
        val location3 = Location(
            latitude = 1.0,
            longitude = 2.0,
            memberId = 2
        )
        ReflectionTestUtils.setField(location1, "id", 1L)
        ReflectionTestUtils.setField(location2, "id", 1L)

        // when, then
        assertThat(setOf(location1, location2, location3)).hasSize(2)
    }
}
