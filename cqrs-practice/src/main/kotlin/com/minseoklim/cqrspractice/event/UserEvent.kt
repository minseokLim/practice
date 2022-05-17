package com.minseoklim.cqrspractice.event

abstract class UserEvent(
    val userId: String
) : Event()
