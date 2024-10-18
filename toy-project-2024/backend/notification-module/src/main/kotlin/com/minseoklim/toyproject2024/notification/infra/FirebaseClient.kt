package com.minseoklim.toyproject2024.notification.infra

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "firebase-client", url = "\${firebase.base-url}")
interface FirebaseClient {
    @PostMapping("/v1/projects/toy-project-2024-621cb/messages:send")
    fun sendMessage(
        @RequestHeader(HttpHeaders.AUTHORIZATION) accessToken: String,
        @RequestBody request: Map<String, Any?>
    ): Map<String, Any?>
}
