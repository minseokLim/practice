package com.minseoklim.queue.ui

import com.minseoklim.queue.application.QueueService
import com.minseoklim.queue.dto.TokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class QueueController(
    private val queueService: QueueService
) {
    @PostMapping("/token")
    fun createToken(): ResponseEntity<TokenResponse> {
        val response = queueService.createToken()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/token")
    fun checkToken(@RequestParam token: String): ResponseEntity<TokenResponse> {
        val response = queueService.checkToken(token)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/token")
    fun deleteToken(@RequestParam token: String): ResponseEntity<Unit> {
        queueService.deleteToken(token)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/access-limit")
    fun setAccessLimit(@RequestParam accessLimit: Long): ResponseEntity<Unit> {
        queueService.setAccessLimit(accessLimit)
        return ResponseEntity.ok().build()
    }
}
