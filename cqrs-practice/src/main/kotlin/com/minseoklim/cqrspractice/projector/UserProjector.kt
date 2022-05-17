package com.minseoklim.cqrspractice.projector

import com.minseoklim.cqrspractice.domain.Address
import com.minseoklim.cqrspractice.domain.Contact
import com.minseoklim.cqrspractice.domain.UserAddress
import com.minseoklim.cqrspractice.domain.UserContact
import com.minseoklim.cqrspractice.event.Event
import com.minseoklim.cqrspractice.event.UserAddressAddedEvent
import com.minseoklim.cqrspractice.event.UserAddressRemovedEvent
import com.minseoklim.cqrspractice.event.UserContactAddedEvent
import com.minseoklim.cqrspractice.event.UserContactRemovedEvent
import com.minseoklim.cqrspractice.event.UserCreatedEvent
import com.minseoklim.cqrspractice.repository.UserReadRepository
import org.springframework.stereotype.Component

@Component
class UserProjector(
    private val readRepository: UserReadRepository
) {
    fun project(event: Event) {
        when (event) {
            is UserCreatedEvent -> apply(event)
            is UserContactAddedEvent -> apply(event)
            is UserContactRemovedEvent -> apply(event)
            is UserAddressAddedEvent -> apply(event)
            is UserAddressRemovedEvent -> apply(event)
        }
    }

    private fun apply(event: UserCreatedEvent) {
        readRepository.userContactStore[event.userId] = UserContact()
        readRepository.userAddressStore[event.userId] = UserAddress()
    }

    private fun apply(event: UserContactAddedEvent) {
        val userContact = readRepository.getUserContact(event.userId).orElse(UserContact())
        val contacts = userContact.contactByType[event.type] ?: mutableSetOf()
        contacts.add(Contact(event.type, event.detail))
        userContact.contactByType[event.type] = contacts
        readRepository.userContactStore[event.userId] = userContact
    }

    private fun apply(event: UserContactRemovedEvent) {
        val userContact = readRepository.getUserContact(event.userId).orElse(UserContact())
        val contacts = userContact.contactByType[event.type] ?: mutableSetOf()
        contacts.remove(Contact(event.type, event.detail))
    }

    private fun apply(event: UserAddressAddedEvent) {
        val userAddress = readRepository.getUserAddress(event.userId).orElse(UserAddress())
        val addresses = userAddress.addressByState[event.state] ?: mutableSetOf()
        addresses.add(Address(event.city, event.state, event.postalCode))
        userAddress.addressByState[event.state] = addresses
        readRepository.userAddressStore[event.userId] = userAddress
    }

    private fun apply(event: UserAddressRemovedEvent) {
        val userAddress = readRepository.getUserAddress(event.userId).orElse(UserAddress())
        val addresses = userAddress.addressByState[event.state] ?: mutableSetOf()
        addresses.remove(Address(event.city, event.state, event.postalCode))
    }
}
