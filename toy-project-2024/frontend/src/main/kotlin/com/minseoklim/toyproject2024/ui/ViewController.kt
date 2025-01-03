package com.minseoklim.toyproject2024.ui

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.net.URLEncoder

@Controller
class ViewController(
    @Value("\${api-base-url}")
    private val apiBaseUrl: String,
    private val textEncryptor: TextEncryptor
) {
    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/login")
    fun login(request: HttpServletRequest): String {
        return "redirect:$apiBaseUrl/login?redirectUrl=${URLEncoder.encode(getBaseUrl(request), "UTF-8")}"
    }

    @GetMapping("/main")
    fun myPage(): String {
        return "main"
    }

    @GetMapping("/social-link")
    fun socialLink(
        request: HttpServletRequest,
        model: Model
    ): String {
        val socialId = textEncryptor.decrypt(request.getParameter("socialId"))
        val socialType = request.getParameter("socialType")

        model.addAttribute("socialId", socialId)
        model.addAttribute("socialType", socialType)

        return "social-link"
    }

    @GetMapping("/make-payment")
    fun makePayment(): String {
        return "make-payment"
    }

    @GetMapping("/chat-rooms")
    fun chatRooms(): String {
        return "chat-rooms"
    }

    @GetMapping("/chat-rooms/{chatRoomId}")
    fun chatRoom(
        @PathVariable chatRoomId: Long,
        model: Model
    ): String {
        model.addAttribute("chatRoomId", chatRoomId)
        return "chat-room"
    }

    @GetMapping("/make-chatting")
    fun makeChatting(): String {
        return "make-chatting"
    }

    @GetMapping("/firebase-token")
    fun getFirebaseToken(): String {
        return "firebase-token"
    }

    @GetMapping("/register-push-token")
    fun registerPushToken(): String {
        return "register-push-token"
    }

    private fun getBaseUrl(request: HttpServletRequest): String {
        val scheme = request.scheme
        val serverName = request.serverName
        val serverPort = request.serverPort

        return if (serverPort == 80 || serverPort == 443) {
            "$scheme://$serverName"
        } else {
            "$scheme://$serverName:$serverPort"
        }
    }
}
