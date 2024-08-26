package com.minseoklim.toyproject2024.product.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntityAndEmbeddable
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
    memberId: Int
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    var name: Name = Name(name)
        protected set

    var price: Price = Price(price)
        protected set

    var stockQuantity: StockQuantity = StockQuantity(stockQuantity)
        protected set

    val memberId: Int = memberId

    var isDeleted: Boolean = false
        protected set

    fun update(other: Product) {
        this.name = other.name
        this.price = other.price
    }

    fun addStockQuantity(increment: Int) {
        if (increment <= 0) {
            throw BadRequestException("INVALID_INCREMENT", "재고 수량을 0 이하로 늘릴 수 없습니다.")
        }
        this.stockQuantity = StockQuantity(this.stockQuantity.value + increment)
    }

    fun removeStockQuantity(decrement: Int) {
        if (decrement <= 0) {
            throw BadRequestException("INVALID_DECREMENT", "재고 수량을 0 이하로 줄일 수 없습니다.")
        }
        if (this.stockQuantity.value - decrement < 0) {
            throw BadRequestException("OUT_OF_STOCK", "재고 수량이 부족합니다.")
        }
        this.stockQuantity = StockQuantity(this.stockQuantity.value - decrement)
    }

    fun isSoldOut(): Boolean {
        return this.stockQuantity.isSoldOut()
    }

    fun checkAuthority(memberId: Int) {
        if (this.memberId != memberId) {
            throw NoPermissionException("NO_PRODUCT_PERMISSION", "상품에 대한 권한이 없습니다.")
        }
    }

    fun delete() {
        this.isDeleted = true
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntityAndEmbeddable(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}
