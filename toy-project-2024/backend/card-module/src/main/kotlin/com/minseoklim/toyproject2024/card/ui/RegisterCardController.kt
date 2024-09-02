package com.minseoklim.toyproject2024.card.ui

import com.minseoklim.toyproject2024.card.application.RegisterCardService
import com.minseoklim.toyproject2024.card.dto.ui.RegisterCardRequest
import com.minseoklim.toyproject2024.card.dto.ui.RegisterCardResponse
import com.minseoklim.toyproject2024.common.annotation.MemberId
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/cards")
class RegisterCardController(
    private val registerCardService: RegisterCardService
) {
    @PostMapping
    fun register(
        @MemberId memberId: Int,
        @Valid @RequestBody request: RegisterCardRequest
    ): ResponseEntity<RegisterCardResponse> {
        val output = registerCardService.register(memberId, request.toInput())
        val uri = URI.create("/cards/${output.id}")
        return ResponseEntity.created(uri).body(RegisterCardResponse.of(output))
    }
}
