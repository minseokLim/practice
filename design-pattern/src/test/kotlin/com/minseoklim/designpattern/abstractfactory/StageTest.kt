package com.minseoklim.designpattern.abstractfactory

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StageTest {

    @Test
    fun create() {
        // when
        val easyStage = Stage(EasyEnemyFactory())

        // then
        assertTrue(easyStage.boss is StrongAttackBoss)
        assertTrue(easyStage.smallFlights.all { it is DashSmallFlight })

        // when
        val hardStage = Stage(HardEnemyFactory())

        // then
        assertTrue(hardStage.boss is CloningBoss)
        assertTrue(hardStage.smallFlights.all { it is MissileSmallFlight })
    }
}
