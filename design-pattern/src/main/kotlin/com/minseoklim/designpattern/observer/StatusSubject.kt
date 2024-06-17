package com.minseoklim.designpattern.observer

abstract class StatusSubject {
    private val observers = mutableListOf<StatusObserver>()

    fun add(observer: StatusObserver) {
        observers.add(observer)
    }

    fun remove(observer: StatusObserver) {
        observers.remove(observer)
    }

    fun notifyObservers(status: Status) {
        observers.forEach { it.onAbnormalStatus(status) }
    }
}
