package com.minseoklim.queue.ui

import com.minseoklim.queue.application.QueueService
import com.minseoklim.queue.exception.BadRequestException
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class ViewController(
    private val queueService: QueueService
) {
    @GetMapping("/queue")
    fun queue(): String {
        return "queue"
    }

    @GetMapping("/main")
    fun main(@RequestParam token: String): String {
        if (!queueService.checkToken(token).isAccessible) {
            throw BadRequestException("INACCESSIBLE_TOKEN", "접속 불가능한 토큰입니다")
        }

        return "main"
    }
}
