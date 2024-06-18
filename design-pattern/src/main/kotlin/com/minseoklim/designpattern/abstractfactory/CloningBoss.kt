package com.minseoklim.designpattern.abstractfactory

class CloningBoss(
    attackPower: Int,
    defensivePower: Int
) : Boss(attackPower, defensivePower) {
    override fun attack() {
        println("Cloning Boss attack")
    }

    override fun specialAttack() {
        println("Cloning Boss special attack")
    }
}
