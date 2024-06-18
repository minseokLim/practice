package com.minseoklim.designpattern.composite

class DeviceGroup : Device {
    private val devices = mutableListOf<Device>()

    override fun addDevice(device: Device) {
        devices.add(device)
    }

    override fun removeDevice(device: Device) {
        devices.remove(device)
    }

    override fun canContain(device: Device): Boolean {
        return true
    }

    override fun turnOn() {
        devices.forEach { it.turnOn() }
    }

    override fun turnOff() {
        devices.forEach { it.turnOff() }
    }
}
