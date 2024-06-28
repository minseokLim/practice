package com.minseoklim.toyproject2024.auth.ui

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ViewController {

    @GetMapping("/login")
    fun login(request: HttpServletRequest): String {
        request.session.setAttribute("REDIRECT_URL", request.getParameter("redirectUrl"))
        return "login"
    }

    @GetMapping("/join")
    fun join(): String {
        return "join"
    }
}
