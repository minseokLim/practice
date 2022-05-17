package com.minseoklim.cqrspractice.domain

class User(
    val userId: String,
    val name: String,
    var contacts: MutableSet<Contact> = mutableSetOf(),
    var addresses: MutableSet<Address> = mutableSetOf()
)
