package com.minseoklim.toyproject2024.location.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.location.application.QueryNearbyLocationService
import com.minseoklim.toyproject2024.location.dto.ui.QueryNearbyLocationResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryNearbyLocationController(
    private val queryNearbyLocationService: QueryNearbyLocationService
) {
    @GetMapping("/locations")
    fun list(
        @MemberId memberId: Long,
        @RequestParam radius: Int
    ): ResponseEntity<List<QueryNearbyLocationResponse>> {
        val outputs = queryNearbyLocationService.list(memberId, radius)
        return ResponseEntity.ok(outputs.map { QueryNearbyLocationResponse.from(it) })
    }
}
