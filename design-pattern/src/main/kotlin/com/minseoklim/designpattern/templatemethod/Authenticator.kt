package com.minseoklim.designpattern.templatemethod

abstract class Authenticator {
    fun authenticate(id: String, password: String): Auth {
        if (!doAuthenticate(id, password)) {
            throw createException()
        }
        return createAuth(id)
    }

    abstract fun doAuthenticate(id: String, password: String): Boolean

    private fun createException(): RuntimeException {
        return RuntimeException("Authentication failed")
    }

    abstract fun createAuth(id: String): Auth
}
