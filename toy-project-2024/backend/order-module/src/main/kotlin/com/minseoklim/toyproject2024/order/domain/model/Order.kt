package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
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
    shippingMessage: String?,
    address: String,
    addressDetail: String,
    zipCode: String,
    receiverName: String,
    receiverPhone: String,
    orderStatus: OrderStatus = OrderStatus.PAYMENT_WAITING,
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

    val shippingInfo: ShippingInfo = ShippingInfo(
        shippingMessage,
        address,
        addressDetail,
        zipCode,
        receiverName,
        receiverPhone
    )

    var orderStatus: OrderStatus = orderStatus
        protected set

    val memberId: Int = memberId

    var paymentId: Int? = null
        protected set

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntityAndEmbeddable(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}