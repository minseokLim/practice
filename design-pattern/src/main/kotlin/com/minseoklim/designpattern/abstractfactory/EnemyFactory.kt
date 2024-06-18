package com.minseoklim.designpattern.abstractfactory

interface EnemyFactory {
    fun createBoss(): Boss
    fun createSmallFlight(): EnemyFlight
}
