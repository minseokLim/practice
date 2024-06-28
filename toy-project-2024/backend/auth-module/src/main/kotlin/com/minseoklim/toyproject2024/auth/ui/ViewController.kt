package com.minseoklim.toyproject2024.auth.ui

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.net.URLEncoder

@Controller
class ViewController {

    @GetMapping("/login")
    fun login(request: HttpServletRequest): String {
        val redirectUrl =
            request.getParameter("redirectUrl") ?: return redirectWithRedirectUrlParameter(request, "/login")
        request.session.setAttribute("REDIRECT_URL", redirectUrl)
        return "login"
    }

    @GetMapping("/join")
    fun join(request: HttpServletRequest): String {
        request.getParameter("redirectUrl") ?: return redirectWithRedirectUrlParameter(request, "/join")
        return "join"
    }

    private fun redirectWithRedirectUrlParameter(request: HttpServletRequest, originalPath: String): String {
        val savedRedirectUrl = request.session.getAttribute("REDIRECT_URL") as String? ?: return "invalidApproach"
        return "redirect:$originalPath?redirectUrl=${URLEncoder.encode(savedRedirectUrl, "UTF-8")}"
    }
}
