package com.minseoklim.toyproject2024.order.domain.model

enum class OrderStatus {
    PAYMENT_WAITING, // 결제 대기
    PREPARING, // 상품 준비 중
    DELIVERING, // 배송 중
    DELIVERY_COMPLETED // 배송 완료
}
