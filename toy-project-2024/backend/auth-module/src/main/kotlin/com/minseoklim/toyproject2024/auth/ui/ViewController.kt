package com.minseoklim.toyproject2024.auth.ui

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {
    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/join")
    fun join(): String {
        return "join"
    }
}
