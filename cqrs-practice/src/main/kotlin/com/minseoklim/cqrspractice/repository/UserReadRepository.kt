package com.minseoklim.cqrspractice.repository

import com.minseoklim.cqrspractice.domain.UserAddress
import com.minseoklim.cqrspractice.domain.UserContact
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class UserReadRepository {
    val userContactStore = mutableMapOf<String, UserContact>()
    val userAddressStore = mutableMapOf<String, UserAddress>()

    fun getUserContact(userId: String): Optional<UserContact> {
        return Optional.ofNullable(userContactStore[userId])
    }

    fun getUserAddress(userId: String): Optional<UserAddress> {
        return Optional.ofNullable(userAddressStore[userId])
    }
}
