package com.minseoklim.toyproject2024.notification.infra

import com.google.auth.oauth2.GoogleCredentials
import com.minseoklim.toyproject2024.notification.application.PushApi
import com.minseoklim.toyproject2024.notification.application.PushApi.PushRequest
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component

@Component
class FirebaseApi(
    private val firebaseClient: FirebaseClient
) : PushApi {
    override fun sendPush(request: PushRequest) {
        val accessToken = "Bearer " + getAccessToken()
        val requestBody = makeRequestBody(request)
        firebaseClient.sendMessage(accessToken, requestBody)
    }

    private fun getAccessToken(): String {
        val googleCredentials = GoogleCredentials.fromStream(ClassPathResource(FIREBASE_KEY_FILE_PATH).inputStream)
            .createScoped("https://www.googleapis.com/auth/firebase.messaging")
        googleCredentials.refreshIfExpired()
        return googleCredentials.accessToken.tokenValue
    }

    private fun makeRequestBody(request: PushRequest): Map<String, Any?> {
        return mapOf(
            "validate_only" to false,
            "message" to mapOf(
                "notification" to mapOf(
                    "title" to request.title,
                    "body" to request.body,
                    "image" to request.imageUrl
                ),
                "token" to request.token
            )
        )
    }

    companion object {
        private const val FIREBASE_KEY_FILE_PATH = "firebase/firebase-key.json"
    }
}
