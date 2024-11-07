package com.minseoklim.toyproject2024.product.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(indexes = [Index(columnList = "member_id")])
class Product(
    name: String,
    price: Long,
    stockQuantity: Int,
    memberId: Long
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var name: Name = Name(name)
        protected set

    var price: Price = Price(price)
        protected set

    var stockQuantity: StockQuantity = StockQuantity(stockQuantity)
        protected set

    val memberId: Long = memberId

    var isDeleted: Boolean = false
        protected set

    fun update(other: Product) {
        this.name = other.name
        this.price = other.price
    }

    fun addStockQuantity(increment: Int) {
        if (increment <= 0) {
            throw BadRequestException(ErrorCode.INVALID_INCREMENT)
        }
        this.stockQuantity = StockQuantity(this.stockQuantity.value + increment)
    }

    fun removeStockQuantity(decrement: Int) {
        if (decrement <= 0) {
            throw BadRequestException(ErrorCode.INVALID_DECREMENT)
        }
        if (this.stockQuantity.value - decrement < 0) {
            throw BadRequestException(ErrorCode.OUT_OF_STOCK)
        }
        this.stockQuantity = StockQuantity(this.stockQuantity.value - decrement)
    }

    fun isSoldOut(): Boolean {
        return this.stockQuantity.isSoldOut()
    }

    fun checkAuthority(memberId: Long) {
        if (this.memberId != memberId) {
            throw NoPermissionException(ErrorCode.NO_PRODUCT_PERMISSION)
        }
    }

    fun delete() {
        this.isDeleted = true
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}
