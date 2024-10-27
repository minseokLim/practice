package com.minseoklim.toyproject2024.location.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.PrecisionModel

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(columnNames = ["member_id"])],
    // using GIST(coordinate) for spatial index
    indexes = [Index(columnList = "coordinate")]
)
class Location(
    latitude: Double,
    longitude: Double,
    memberId: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    @Column(columnDefinition = "geometry(Point, 4326)")
    var coordinate: Point = geometryFactory.createPoint(Coordinate(longitude, latitude))
        protected set

    val memberId: Int = memberId

    fun setCoordinate(
        latitude: Double,
        longitude: Double
    ) {
        this.coordinate = geometryFactory.createPoint(Coordinate(longitude, latitude))
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()

    companion object {
        private val geometryFactory = GeometryFactory(PrecisionModel(), 4326)
    }
}
