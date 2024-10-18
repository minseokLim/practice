package com.minseoklim.toyproject2024.notification.application

interface PushApi {
    fun sendPush(request: PushRequest)

    data class PushRequest(
        val title: String,
        val body: String,
        val imageUrl: String? = null,
        val token: String
    )
}
