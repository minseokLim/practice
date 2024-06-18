package com.minseoklim.designpattern.abstractfactory

class HardEnemyFactory : EnemyFactory {
    override fun createBoss(): Boss {
        return CloningBoss(40, 20)
    }

    override fun createSmallFlight(): EnemyFlight {
        return MissileSmallFlight(20, 10)
    }
}
