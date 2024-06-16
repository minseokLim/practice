package com.minseoklim.designpattern.strategy

interface DiscountStrategy {
    fun getDiscountedPrice(item: Item): Int
}
