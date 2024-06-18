package com.minseoklim.designpattern.composite

class Light : Device {
    override fun addDevice(device: Device) {
        throw UnsupportedOperationException("Light cannot contain any device")
    }

    override fun removeDevice(device: Device) {
        throw UnsupportedOperationException("Light cannot contain any device")
    }

    override fun canContain(device: Device): Boolean {
        return false
    }

    override fun turnOn() {
        println("Light is on")
    }

    override fun turnOff() {
        println("Light is off")
    }
}
