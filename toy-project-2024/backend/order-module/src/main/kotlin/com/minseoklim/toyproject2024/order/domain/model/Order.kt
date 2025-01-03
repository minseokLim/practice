package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
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
    paymentId: Long? = null,
    memberId: Long
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val orderName: OrderName = OrderName(orderName)

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_product", joinColumns = [JoinColumn(name = "order_id")])
    @OrderColumn(name = "order_product_idx")
    val orderProducts: List<OrderProduct> = orderProducts

    val shippingInfo: ShippingInfo = shippingInfo

    @Enumerated(EnumType.STRING)
    var orderStatus: OrderStatus = orderStatus
        protected set

    var paymentId: Long? = paymentId
        protected set

    val memberId: Long = memberId

    var isCanceled: Boolean = false
        protected set

    val isPaymentWaiting: Boolean
        get() = this.orderStatus == OrderStatus.PAYMENT_WAITING

    fun applyPaymentId(paymentId: Long) {
        this.paymentId = paymentId
    }

    fun changeToPreparing() {
        this.orderStatus = OrderStatus.PREPARING
    }

    fun checkAuthority(memberId: Long) {
        if (this.memberId != memberId) {
            throw NoPermissionException(ErrorCode.NO_ORDER_PERMISSION)
        }
    }

    fun cancel() {
        this.isCanceled = true
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}
