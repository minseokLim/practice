package com.minseoklim.toyproject2024.common.domain.type

enum class ErrorCode(
    val message: String
) {
    INVALID_FILTER_KEY("유효하지 않은 필터 키입니다."),
    MEMBER_NOT_FOUND("찾을 수 없는 회원입니다."),
    ROLE_DUPLICATED("이미 등록된 권한입니다."),
    ROLE_REQUIRED("최소 1개 이상의 권한은 필수입니다."),
    SOCIAL_LINK_DUPLICATED("이미 연동된 소셜 계정입니다."),
    SOCIAL_LINK_REQUIRED("로그인 ID가 없는 경우, 1개 이상의 소셜 계정 연동은 필수입니다."),
    PASSWORD_REQUIRED("로그인 ID가 있는 경우, 비밀번호는 필수입니다."),
    PASSWORD_NOT_ALLOWED("로그인 ID가 없는 경우, 비밀번호는 허용되지 않습니다."),
    OCCUPIED_LOGIN_ID("이미 사용 중인 로그인 ID입니다."),
    ORDER_NOT_FOUND("찾을 수 없는 주문 정보입니다."),
    NO_ORDER_PERMISSION("주문에 대한 권한이 없습니다."),
    INVALID_TOKEN("유효하지 않은 토큰입니다."),
    INVALID_FILTER_FORMAT("유효하지 않은 필터 형식입니다."),
    PAYMENT_NOT_FOUND("찾을 수 없는 결제 정보입니다."),
    NOT_VERIFIED_PAYMENT("인증 결제 정보가 아닙니다."),
    NO_PAYMENT_PERMISSION("결제에 대한 권한이 없습니다."),
    CARD_NOT_FOUND("찾을 수 없는 카드입니다."),
    NO_CARD_PERMISSION("카드에 대한 권한이 없습니다."),
    ROOM_NOT_FOUND("찾을 수 없는 채팅 방입니다."),
    NO_CHAT_ROOM_PERMISSION("채팅방에 대한 권한이 없습니다."),
    NO_MESSAGE_PERMISSION("메시지에 대한 권한이 없습니다."),
    PUSH_TOKEN_NOT_FOUND("푸시 토큰을 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND("찾을 수 없는 상품입니다."),
    INVALID_INCREMENT("재고 수량을 0 이하로 늘릴 수 없습니다."),
    INVALID_DECREMENT("재고 수량을 0 이하로 줄일 수 없습니다."),
    OUT_OF_STOCK("재고 수량이 부족합니다."),
    NO_PRODUCT_PERMISSION("상품에 대한 권한이 없습니다.")
}
