package com.minseoklim.cqrspractice.domain

class UserAddress(
    val addressByState: MutableMap<String, MutableSet<Address>> = mutableMapOf()
)
