package com.minseoklim.designpattern.composite

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.assertDoesNotThrow

class PowerControllerTest {

    @Test
    fun turnOn() {
        // given
        val light: Device = Light()
        val powerController1 = PowerController(light)

        // when, then
        assertDoesNotThrow { powerController1.turnOn() }

        // given
        val aircon: Device = Aircon()
        val deviceGroup: Device = DeviceGroup()
        if (deviceGroup.canContain(light)) {
            deviceGroup.addDevice(light)
        }
        if (deviceGroup.canContain(aircon)) {
            deviceGroup.addDevice(aircon)
        }
        val powerController2 = PowerController(deviceGroup)

        // when, then
        assertDoesNotThrow { powerController2.turnOn() }

        // given
        val lightGroup: Device = DeviceGroup()
        if (lightGroup.canContain(light)) {
            lightGroup.addDevice(light)
        }
        val airconGroup: Device = DeviceGroup()
        if (airconGroup.canContain(aircon)) {
            airconGroup.addDevice(aircon)
        }
        val totalGroup: Device = DeviceGroup()
        if (totalGroup.canContain(lightGroup)) {
            totalGroup.addDevice(lightGroup)
        }
        if (totalGroup.canContain(airconGroup)) {
            totalGroup.addDevice(airconGroup)
        }
        val powerController3 = PowerController(totalGroup)

        // when, then
        assertDoesNotThrow { powerController3.turnOn() }
    }

    @Test
    fun turnOff() {
        // given
        val light: Device = Light()
        val powerController1 = PowerController(light)

        // when, then
        assertDoesNotThrow { powerController1.turnOff() }

        // given
        val aircon: Device = Aircon()
        val deviceGroup: Device = DeviceGroup()
        if (deviceGroup.canContain(light)) {
            deviceGroup.addDevice(light)
        }
        if (deviceGroup.canContain(aircon)) {
            deviceGroup.addDevice(aircon)
        }
        val powerController2 = PowerController(deviceGroup)

        // when, then
        assertDoesNotThrow { powerController2.turnOff() }

        // given
        val lightGroup: Device = DeviceGroup()
        if (lightGroup.canContain(light)) {
            lightGroup.addDevice(light)
        }
        val airconGroup: Device = DeviceGroup()
        if (airconGroup.canContain(aircon)) {
            airconGroup.addDevice(aircon)
        }
        val totalGroup: Device = DeviceGroup()
        if (totalGroup.canContain(lightGroup)) {
            totalGroup.addDevice(lightGroup)
        }
        if (totalGroup.canContain(airconGroup)) {
            totalGroup.addDevice(airconGroup)
        }
        val powerController3 = PowerController(totalGroup)

        // when, then
        assertDoesNotThrow { powerController3.turnOff() }
    }
}
