package com.minseoklim.toyproject2024.member.event

import com.minseoklim.toyproject2024.common.event.DomainEvent

class MemberDeletedEvent(
    source: Any,
    val memberId: Long
) : DomainEvent(source)
