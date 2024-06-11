package com.minseoklim.toyproject2024.common.exception

class BadRequestException(
    code: String,
    message: String
) : CommonException(code, message)
