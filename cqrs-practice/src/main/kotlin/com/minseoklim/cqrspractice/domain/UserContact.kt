package com.minseoklim.cqrspractice.domain

class UserContact(
    val contactByType: MutableMap<String, MutableSet<Contact>> = mutableMapOf()
)
