package com.minseoklim.cqrspractice.util

import com.minseoklim.cqrspractice.domain.Address
import com.minseoklim.cqrspractice.domain.Contact
import com.minseoklim.cqrspractice.domain.User
import com.minseoklim.cqrspractice.event.UserAddressAddedEvent
import com.minseoklim.cqrspractice.event.UserAddressRemovedEvent
import com.minseoklim.cqrspractice.event.UserContactAddedEvent
import com.minseoklim.cqrspractice.event.UserContactRemovedEvent
import com.minseoklim.cqrspractice.event.UserCreatedEvent
import com.minseoklim.cqrspractice.repository.EventStore

object UserUtility {
    fun recreateUserState(store: EventStore, userId: String): User {
        val events = store.getUserEvents(userId)

        if (events.isEmpty() || events[0] !is UserCreatedEvent) {
            throw IllegalArgumentException()
        }

        val userCreatedEvent = events[0] as UserCreatedEvent
        val user = User(userCreatedEvent.userId, userCreatedEvent.name)

        for (i in 1 until events.size) {
            when (val event = events[i]) {
                is UserAddressAddedEvent -> {
                    val address = Address(event.city, event.state, event.postalCode)
                    user.addresses.add(address)
                }
                is UserAddressRemovedEvent -> {
                    val address = Address(event.city, event.state, event.postalCode)
                    user.addresses.remove(address)
                }
                is UserContactAddedEvent -> {
                    val contact = Contact(event.type, event.detail)
                    user.contacts.add(contact)
                }
                is UserContactRemovedEvent -> {
                    val contact = Contact(event.type, event.detail)
                    user.contacts.remove(contact)
                }
            }
        }

        return user
    }
}
