package com.minseoklim.toyproject2024.auth.ui

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController(
    @Value("\${frontend.base-url}") private val frontendBaseUrl: String
) {
    @GetMapping("/login")
    fun login(model: Model): String {
        model.addAttribute("frontendBaseUrl", frontendBaseUrl)
        return "login"
    }

    @GetMapping("/join")
    fun join(): String {
        return "join"
    }
}
