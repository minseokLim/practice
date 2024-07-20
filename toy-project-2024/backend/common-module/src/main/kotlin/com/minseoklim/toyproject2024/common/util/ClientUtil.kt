package com.minseoklim.toyproject2024.common.util

import jakarta.servlet.http.HttpServletRequest

object ClientUtil {
    fun HttpServletRequest.getClientIp(): String {
        var ip = this.getHeader("X-Forwarded-For")
        if (ip.isNullOrEmpty() || "unknown".equals(ip, ignoreCase = true)) {
            ip = this.getHeader("X-Real-IP")
        }
        if (ip.isNullOrEmpty() || "unknown".equals(ip, ignoreCase = true)) {
            ip = this.remoteAddr
        }
        return ip
    }

    fun HttpServletRequest.getUserAgent(): String {
        return this.getHeader("User-Agent")
    }
}
