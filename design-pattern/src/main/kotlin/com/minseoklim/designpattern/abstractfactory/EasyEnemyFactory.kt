package com.minseoklim.designpattern.abstractfactory

class EasyEnemyFactory : EnemyFactory {
    override fun createBoss(): Boss {
        return StrongAttackBoss(20, 10)
    }

    override fun createSmallFlight(): EnemyFlight {
        return DashSmallFlight(10, 5)
    }
}
