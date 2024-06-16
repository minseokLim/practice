package com.minseoklim.designpattern.state

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VendingMachineTest {

    @Test
    fun insertCoinAndSelect() {
        // given
        val productDao = mapOf(
            1 to Product(1, "Coke", 100),
            2 to Product(2, "Sprite", 150)
        )
        val vendingMachine = VendingMachine(productDao)

        // when
        vendingMachine.insertCoin(100)

        // then
        assertEquals(100, vendingMachine.coin)
        assertEquals(State.SELECTABLE, vendingMachine.state)

        // when
        vendingMachine.select(1)

        // then
        assertEquals(0, vendingMachine.coin)
        assertEquals(State.NO_COIN, vendingMachine.state)
    }
}
