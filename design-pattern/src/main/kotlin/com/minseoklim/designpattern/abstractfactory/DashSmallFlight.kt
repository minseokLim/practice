package com.minseoklim.designpattern.abstractfactory

class DashSmallFlight(
    attackPower: Int,
    defensivePower: Int
) : SmallFlight(attackPower, defensivePower) {
    override fun attack() {
        println("Dash Small Flight attack")
    }
}
