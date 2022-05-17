package com.minseoklim.cqrspractice.event

class UserAddressAddedEvent(
    userId: String,
    val city: String,
    val state: String,
    val postalCode: String
) : UserEvent(userId)
