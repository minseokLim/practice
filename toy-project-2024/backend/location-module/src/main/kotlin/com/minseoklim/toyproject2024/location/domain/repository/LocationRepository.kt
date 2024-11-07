package com.minseoklim.toyproject2024.location.domain.repository

import com.minseoklim.toyproject2024.location.domain.model.Location
import org.locationtech.jts.geom.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LocationRepository : JpaRepository<Location, Long> {
    fun findByMemberId(memberId: Long): Location?

    @Query("SELECT l FROM Location l WHERE ST_DWithin(l.coordinate, :point, :distance) is true")
    fun findAllByDistance(
        point: Point,
        distance: Int
    ): List<Location>
}
