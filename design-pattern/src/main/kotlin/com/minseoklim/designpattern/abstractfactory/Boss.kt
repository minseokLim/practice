package com.minseoklim.designpattern.abstractfactory

abstract class Boss(
    attackPower: Int,
    defensivePower: Int
) : EnemyFlight(attackPower, defensivePower) {
    abstract fun specialAttack()
}
