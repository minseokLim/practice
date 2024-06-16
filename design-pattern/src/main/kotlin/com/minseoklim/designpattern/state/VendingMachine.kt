package com.minseoklim.designpattern.state

class VendingMachine(
    private val productDao: Map<Int, Product>
) {
    var state: State = State.NO_COIN
    var coin: Int = 0

    fun insertCoin(coin: Int) {
        state.insertCoin(coin, this)
    }

    fun select(productId: Int) {
        state.select(productId, this)
    }

    fun increaseCoin(coin: Int) {
        this.coin += coin
    }

    fun decreaseCoin(coin: Int) {
        this.coin -= coin
    }

    fun hasCoin(): Boolean {
        return coin > 0
    }

    fun provideProduct(productId: Int): Product {
        return productDao[productId]?.apply {
            println("Product ${this.name} is provided")
        } ?: throw IllegalArgumentException("Product not found")
    }

    fun changeState(state: State) {
        this.state = state
    }
}
