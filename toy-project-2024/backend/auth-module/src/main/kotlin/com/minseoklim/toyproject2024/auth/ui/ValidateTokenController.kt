package com.minseoklim.toyproject2024.auth.ui

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ValidateTokenController {
    @GetMapping("/validate-token")
    fun validateToken(): ResponseEntity<Unit> {
        return ResponseEntity.ok().build() // JwtFilter에서 검증되었기 때문에 200 OK 반환
    }
}
