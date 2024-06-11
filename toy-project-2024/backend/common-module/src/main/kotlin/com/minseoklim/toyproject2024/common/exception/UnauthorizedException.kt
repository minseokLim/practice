package com.minseoklim.toyproject2024.common.exception

class UnauthorizedException(
    code: String,
    message: String
) : CommonException(code, message)
