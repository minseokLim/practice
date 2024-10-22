package com.minseoklim.toyproject2024.notification.infra

import com.minseoklim.toyproject2024.notification.application.PushApi
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Primary
@Component
class DummyPushApi : PushApi {
    override fun sendPush(request: PushApi.PushRequest) {
        // do nothing
    }
}
