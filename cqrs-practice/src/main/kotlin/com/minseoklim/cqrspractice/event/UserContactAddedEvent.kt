package com.minseoklim.cqrspractice.event

class UserContactAddedEvent(
    userId: String,
    val type: String,
    val detail: String
) : UserEvent(userId)
