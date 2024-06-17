package com.minseoklim.designpattern.decorator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class DecoratorTest {

    @Test
    fun write() {
        // given
        val fileOut = FileOutImpl()
        val encryptionOut = EncryptionOut(fileOut)

        // when, then
        assertDoesNotThrow { encryptionOut.write("data") }

        // given
        val zipEncryptionOut = ZipOut(EncryptionOut(fileOut))

        // when, then
        assertDoesNotThrow { zipEncryptionOut.write("data") }

        // given
        val encryptionZipOut = EncryptionOut(ZipOut(fileOut))

        // when, then
        assertDoesNotThrow { encryptionZipOut.write("data") }
    }
}
