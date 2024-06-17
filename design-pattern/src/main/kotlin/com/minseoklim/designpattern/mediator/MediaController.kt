package com.minseoklim.designpattern.mediator

class MediaController {
    private val controllerObservers: MutableList<ControllerObserver> = mutableListOf()

    fun addObserver(observer: ControllerObserver) {
        controllerObservers.add(observer)
    }

    fun removeObserver(observer: ControllerObserver) {
        controllerObservers.remove(observer)
    }

    fun volumeChanged(volume: Int) {
        println("Volume changed to $volume on media controller")
        controllerObservers.forEach { it.volumeChanged(volume) }
    }
}
