package com.minseoklim.designpattern.composite

class Aircon : Device {
    override fun addDevice(device: Device) {
        throw UnsupportedOperationException("Aircon cannot contain any device")
    }

    override fun removeDevice(device: Device) {
        throw UnsupportedOperationException("Aircon cannot contain any device")
    }

    override fun canContain(device: Device): Boolean {
        return false
    }

    override fun turnOn() {
        println("Aircon is on")
    }

    override fun turnOff() {
        println("Aircon is off")
    }
}
