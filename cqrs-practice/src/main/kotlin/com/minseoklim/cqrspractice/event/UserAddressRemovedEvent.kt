package com.minseoklim.cqrspractice.event

class UserAddressRemovedEvent(
    userId: String,
    val city: String,
    val state: String,
    val postalCode: String
) : UserEvent(userId)
