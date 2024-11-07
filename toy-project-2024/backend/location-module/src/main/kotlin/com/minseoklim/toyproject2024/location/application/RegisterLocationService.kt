package com.minseoklim.toyproject2024.location.application

import com.minseoklim.toyproject2024.location.domain.repository.LocationRepository
import com.minseoklim.toyproject2024.location.dto.application.RegisterLocationInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterLocationService(
    private val locationRepository: LocationRepository
) {
    fun register(
        memberId: Long,
        input: RegisterLocationInput
    ) {
        val location = locationRepository.findByMemberId(memberId)
        if (location != null) {
            location.setCoordinate(input.latitude, input.longitude)
        } else {
            locationRepository.save(input.toEntity(memberId))
        }
    }
}
