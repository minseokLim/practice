package com.minseoklim.designpattern.abstractfactory

class StrongAttackBoss(
    attackPower: Int,
    defensivePower: Int
) : Boss(attackPower, defensivePower) {
    override fun attack() {
        println("Strong Attack Boss attack")
    }

    override fun specialAttack() {
        println("Strong Attack Boss special attack")
    }
}
