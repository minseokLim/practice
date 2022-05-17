package com.minseoklim.cqrspractice.event

class UserCreatedEvent(
    userId: String,
    val name: String
) : UserEvent(userId)
