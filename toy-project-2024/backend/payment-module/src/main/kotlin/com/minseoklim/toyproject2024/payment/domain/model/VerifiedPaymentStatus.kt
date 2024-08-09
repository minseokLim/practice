package com.minseoklim.toyproject2024.payment.domain.model

enum class VerifiedPaymentStatus {
    CREATED, // 생성됨
    FAILED, // 결제 실패
    COMPLETED, // 검증 완료
    TAMPERED // 결제 정보 변조됨
}
