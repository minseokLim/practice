package com.minseoklim.designpattern.templatemethod

class DbAuthenticator(
    private val userDao: Map<String, User>
) : Authenticator() {
    override fun doAuthenticate(id: String, password: String): Boolean {
        return userDao[id]?.let {
            it.password == password
        } ?: false
    }

    override fun createAuth(id: String): Auth {
        return userDao[id]?.let {
            Auth(it.id, it.username)
        } ?: throw RuntimeException("User not found")
    }
}
