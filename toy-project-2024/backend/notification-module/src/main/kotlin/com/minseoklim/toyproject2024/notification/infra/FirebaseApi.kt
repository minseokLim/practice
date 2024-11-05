package com.minseoklim.toyproject2024.notification.infra

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.AndroidConfig
import com.google.firebase.messaging.AndroidNotification
import com.google.firebase.messaging.ApnsConfig
import com.google.firebase.messaging.ApnsFcmOptions
import com.google.firebase.messaging.Aps
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import com.google.firebase.messaging.WebpushConfig
import com.minseoklim.toyproject2024.notification.application.PushApi
import com.minseoklim.toyproject2024.notification.application.PushApi.PushRequest
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class FirebaseApi : PushApi {
    @PostConstruct
    fun postConstruct() {
        if (FirebaseApp.getApps().isEmpty()) {
            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(ClassPathResource(FIREBASE_KEY_FILE_PATH).inputStream))
                .build()
            FirebaseApp.initializeApp(options)
        }
    }

    override fun sendPush(request: PushRequest) {
        val message = Message.builder()
            .setToken(request.token)
            .setNotification(getNotification(title = request.title, body = request.body, imageUrl = request.imageUrl))
            .apply {
                if (request.imageUrl != null) {
                    setAndroidConfig(getAndroidConfig(request.imageUrl))
                    setApnsConfig(getApnsConfig(request.imageUrl))
                    setWebpushConfig(getWebpushConfig(request.imageUrl))
                }
            }
            .build()

        FirebaseMessaging.getInstance().send(message)
    }

    private fun getNotification(
        title: String,
        body: String,
        imageUrl: String?
    ): Notification {
        return Notification.builder()
            .setTitle(title)
            .setBody(body)
            .setImage(imageUrl)
            .build()
    }

    private fun getAndroidConfig(imageUrl: String): AndroidConfig {
        return AndroidConfig.builder()
            .setNotification(
                AndroidNotification.builder()
                    .setImage(imageUrl)
                    .build()
            )
            .build()
    }

    private fun getApnsConfig(imageUrl: String): ApnsConfig {
        return ApnsConfig.builder()
            .setAps(
                Aps.builder()
                    .setContentAvailable(true)
                    .setMutableContent(true)
                    .build()
            )
            .setFcmOptions(
                ApnsFcmOptions.builder()
                    .setImage(imageUrl)
                    .build()
            )
            .build()
    }

    private fun getWebpushConfig(imageUrl: String): WebpushConfig {
        return WebpushConfig.builder()
            .putHeader("image", imageUrl)
            .build()
    }

    companion object {
        private const val FIREBASE_KEY_FILE_PATH = "firebase/firebase-key.json"
    }
}
