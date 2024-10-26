package com.minseoklim.toyproject2024.location.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.location.application.RegisterLocationService
import com.minseoklim.toyproject2024.location.dto.ui.RegisterLocationRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterLocationController(
    private val registerLocationService: RegisterLocationService
) {
    @PutMapping("/locations")
    fun register(
        @MemberId memberId: Int,
        @Valid @RequestBody request: RegisterLocationRequest
    ): ResponseEntity<Unit> {
        registerLocationService.register(memberId, request.toInput())
        return ResponseEntity.ok().build()
    }
}
