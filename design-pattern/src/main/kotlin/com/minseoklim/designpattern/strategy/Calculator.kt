package com.minseoklim.designpattern.strategy

class Calculator(
    private val discountStrategy: DiscountStrategy
) {
    fun calculate(items: List<Item>): Int {
        return items.sumOf { discountStrategy.getDiscountedPrice(it) }
    }
}
