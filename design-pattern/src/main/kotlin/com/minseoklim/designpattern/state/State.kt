package com.minseoklim.designpattern.state

enum class State {
    NO_COIN {
        override fun insertCoin(coin: Int, vm: VendingMachine) {
            vm.increaseCoin(coin)
            vm.changeState(SELECTABLE)
        }

        override fun select(productId: Int, vm: VendingMachine) {
            println("Please insert coin first")
        }
    },
    SELECTABLE {
        override fun insertCoin(coin: Int, vm: VendingMachine) {
            vm.increaseCoin(coin)
        }

        override fun select(productId: Int, vm: VendingMachine) {
            val product = vm.provideProduct(productId)
            vm.decreaseCoin(product.price)

            if (!vm.hasCoin()) {
                vm.changeState(NO_COIN)
            }
        }
    };

    abstract fun insertCoin(coin: Int, vm: VendingMachine)
    abstract fun select(productId: Int, vm: VendingMachine)
}
