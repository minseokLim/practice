package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntityAndEmbeddable
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.OrderColumn
import jakarta.persistence.Table

@Entity
@Table(name = "orders", indexes = [Index(columnList = "member_id")])
class Order(
    orderName: String,
    orderProducts: List<OrderProduct>,
    shippingInfo: ShippingInfo,
    orderStatus: OrderStatus = OrderStatus.PAYMENT_WAITING,
    paymentId: Int? = null,
    memberId: Int
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    val orderName: OrderName = OrderName(orderName)

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_product", joinColumns = [JoinColumn(name = "order_id")])
    @OrderColumn(name = "order_product_idx")
    val orderProducts: List<OrderProduct> = orderProducts

    val shippingInfo: ShippingInfo = shippingInfo

    var orderStatus: OrderStatus = orderStatus
        protected set

    var paymentId: Int? = paymentId
        protected set

    val memberId: Int = memberId

    var isCanceled: Boolean = false
        protected set

    val isPaymentWaiting: Boolean
        get() = this.orderStatus == OrderStatus.PAYMENT_WAITING

    fun applyPaymentId(paymentId: Int) {
        this.paymentId = paymentId
    }

    fun changeToPreparing() {
        this.orderStatus = OrderStatus.PREPARING
    }

    fun checkAuthority(memberId: Int) {
        if (this.memberId != memberId) {
            throw NoPermissionException("NO_ORDER_PERMISSION", "주문에 대한 권한이 없습니다.")
        }
    }

    fun cancel() {
        this.isCanceled = true
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntityAndEmbeddable(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}
