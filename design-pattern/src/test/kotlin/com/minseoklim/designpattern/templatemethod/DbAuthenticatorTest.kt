package com.minseoklim.designpattern.templatemethod

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DbAuthenticatorTest {

    @Test
    fun authenticate() {
        // given
        val dbAuthenticator = DbAuthenticator(mapOf("mslim" to User("mslim", "minseok", "password")))

        // when
        val auth = dbAuthenticator.authenticate("mslim", "password")

        // then
        assertEquals("mslim", auth.id)
        assertEquals("minseok", auth.username)
    }
}
