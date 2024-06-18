package com.minseoklim.designpattern.composite

interface Device {
    fun addDevice(device: Device)
    fun removeDevice(device: Device)
    fun canContain(device: Device): Boolean
    fun turnOn()
    fun turnOff()
}
