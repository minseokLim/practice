package com.minseoklim.toyproject2024.common.exception

class NotFoundException(
    code: String,
    message: String
) : CommonException(code, message)
