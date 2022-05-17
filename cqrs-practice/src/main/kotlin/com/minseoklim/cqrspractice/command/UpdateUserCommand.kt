package com.minseoklim.cqrspractice.command

import com.minseoklim.cqrspractice.domain.Address
import com.minseoklim.cqrspractice.domain.Contact

class UpdateUserCommand(
    val userId: String,
    val contacts: Set<Contact>,
    val addresses: Set<Address>
)
