package com.minseoklim.cqrspractice.domain

import com.minseoklim.cqrspractice.query.AddressByStateQuery
import com.minseoklim.cqrspractice.query.ContactByTypeQuery
import com.minseoklim.cqrspractice.repository.UserReadRepository
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class UserProjection(
    private val readRepository: UserReadRepository
) {
    fun handleContactByTypeQuery(query: ContactByTypeQuery): Optional<Set<Contact>> {
        val userContact = readRepository.getUserContact(query.userId).orElseThrow()
        return Optional.ofNullable(userContact.contactByType[query.type])
    }

    fun handleAddressByStateQuery(query: AddressByStateQuery): Optional<Set<Address>> {
        val userAddress = readRepository.getUserAddress(query.userId).orElseThrow()
        return Optional.ofNullable(userAddress.addressByState[query.state])
    }
}
