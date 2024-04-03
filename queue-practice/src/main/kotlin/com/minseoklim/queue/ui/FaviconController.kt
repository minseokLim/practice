package com.minseoklim.queue.ui

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class FaviconController {
    @GetMapping("/favicon.ico")
    fun returnNoFavicon() {
    }
}
