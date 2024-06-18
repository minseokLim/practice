package com.minseoklim.designpattern.abstractfactory

abstract class EnemyFlight(
    val attackPower: Int,
    val defensivePower: Int
) {
    abstract fun attack()
}
