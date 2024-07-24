package com.minseoklim.toyproject2024.card.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.test.util.ReflectionTestUtils

class CardTest {

    @BeforeEach
    fun setUp() {
        TextEncryptUtil.init(Encryptors.noOpText())
    }

    @Test
    fun delete() {
        // given
        val card = Card(
            cardNumber = "1234-5678-1234-5678",
            cardExpiry = "2025-12",
            birth = "990101",
            pwd2digit = "12",
            issuerName = "삼성카드",
            memberId = 1
        )

        // when
        card.delete()

        // then
        assertThat(card.isDeleted).isTrue
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val card1 = Card(
            cardNumber = "1234-5678-1234-5678",
            cardExpiry = "2025-12",
            birth = "990101",
            pwd2digit = "12",
            issuerName = "삼성카드",
            memberId = 1
        )
        val card2 = Card(
            cardNumber = "9876-5432-9876-5432",
            cardExpiry = "2025-11",
            birth = "991231",
            pwd2digit = "34",
            issuerName = "우리카드",
            memberId = 1
        )
        val card3 = Card(
            cardNumber = "9876-5432-9876-5432",
            cardExpiry = "2025-11",
            birth = "991231",
            pwd2digit = "34",
            issuerName = "우리카드",
            memberId = 1
        )
        ReflectionTestUtils.setField(card1, "id", 1)
        ReflectionTestUtils.setField(card2, "id", 1)

        // when, then
        TestUtil.testEqualsAndHashCode(card1, card2, card3)
    }
}
