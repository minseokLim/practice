package com.minseoklim.designpattern.strategy

class FirstGuestDiscountStrategy : DiscountStrategy {
    override fun getDiscountedPrice(item: Item): Int {
        return item.price * 90 / 100
    }
}
