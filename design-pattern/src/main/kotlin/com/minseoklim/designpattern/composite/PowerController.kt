package com.minseoklim.designpattern.composite

class PowerController(
    private val device: Device
) {
    fun turnOn() {
        device.turnOn()
    }

    fun turnOff() {
        device.turnOff()
    }
}
