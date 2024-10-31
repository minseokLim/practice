package com.minseoklim.toyproject2024.common.exception

import com.minseoklim.toyproject2024.common.domain.type.ErrorCode

class BadRequestException(
    code: ErrorCode
) : CommonException(code)
