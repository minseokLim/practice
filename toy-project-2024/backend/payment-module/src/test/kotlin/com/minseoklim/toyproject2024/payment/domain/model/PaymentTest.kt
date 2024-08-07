package com.minseoklim.toyproject2024.payment.domain.model

import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class PaymentTest {

    @Test
    fun checkAuthority() {
        // given
        val cardPayment = CardPayment(
            amount = 1000L,
            productName = "테스트 상품",
            memberId = 1,
            cardId = 1
        )

        // when, then
        assertThatNoException().isThrownBy {
            cardPayment.checkAuthority(1)
        }

        // when, then
        assertThatThrownBy {
            cardPayment.checkAuthority(2)
        }.isInstanceOf(NoPermissionException::class.java)
    }

    @Test
    fun cancel() {
        // given
        val cardPayment = CardPayment(
            amount = 1000L,
            productName = "테스트 상품",
            memberId = 1,
            cardId = 1
        )

        // when
        cardPayment.cancel()

        // then
        assertThat(cardPayment.isCanceled).isTrue
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val cardPayment1 = CardPayment(
            amount = 1000L,
            productName = "테스트 상품",
            memberId = 1,
            cardId = 1
        )
        val cardPayment2 = CardPayment(
            amount = 999L,
            productName = "다른 테스트 상품",
            memberId = 2,
            cardId = 2
        )
        val cardPayment3 = CardPayment(
            amount = 999L,
            productName = "다른 테스트 상품",
            memberId = 2,
            cardId = 2
        )
        ReflectionTestUtils.setField(cardPayment1, "id", 1)
        ReflectionTestUtils.setField(cardPayment2, "id", 1)

        // when, then
        TestUtil.testEqualsAndHashCode(cardPayment1, cardPayment2, cardPayment3)
    }
}
