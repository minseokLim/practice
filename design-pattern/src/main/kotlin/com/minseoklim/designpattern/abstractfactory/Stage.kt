package com.minseoklim.designpattern.abstractfactory

class Stage(
    enemyFactory: EnemyFactory
) {
    val boss = enemyFactory.createBoss()
    val smallFlights = mutableListOf<EnemyFlight>().apply {
        repeat(10) {
            add(enemyFactory.createSmallFlight())
        }
    }
}
