package com.minseoklim.queue.exception

class BadRequestException(
    code: String,
    message: String
) : CommonException(code, message)
