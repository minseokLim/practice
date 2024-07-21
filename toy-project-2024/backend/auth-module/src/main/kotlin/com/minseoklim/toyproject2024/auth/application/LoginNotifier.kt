package com.minseoklim.toyproject2024.auth.application

import java.time.LocalDateTime

interface LoginNotifier {
    fun notifyLogin(memberId: Int, clientIp: String, userAgent: String, loginDateTime: LocalDateTime)
}
