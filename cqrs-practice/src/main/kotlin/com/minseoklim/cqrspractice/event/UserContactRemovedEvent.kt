package com.minseoklim.cqrspractice.event

class UserContactRemovedEvent(
    userId: String,
    val type: String,
    val detail: String
) : UserEvent(userId)
