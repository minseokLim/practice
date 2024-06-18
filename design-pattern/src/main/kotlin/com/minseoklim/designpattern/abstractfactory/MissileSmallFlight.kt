package com.minseoklim.designpattern.abstractfactory

class MissileSmallFlight(
    attackPower: Int,
    defensivePower: Int
) : SmallFlight(attackPower, defensivePower) {
    override fun attack() {
        println("Missile Small Flight attack")
    }
}
