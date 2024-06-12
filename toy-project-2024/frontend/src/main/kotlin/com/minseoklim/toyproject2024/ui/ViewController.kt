package com.minseoklim.toyproject2024.ui

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {

    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/join")
    fun join(): String {
        return "join"
    }

    @GetMapping("/main")
    fun myPage(): String {
        return "main"
    }
}
