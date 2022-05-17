package com.minseoklim.cqrspractice.domain

import com.minseoklim.cqrspractice.command.CreateUserCommand
import com.minseoklim.cqrspractice.command.UpdateUserCommand
import com.minseoklim.cqrspractice.event.Event
import com.minseoklim.cqrspractice.event.UserAddressAddedEvent
import com.minseoklim.cqrspractice.event.UserAddressRemovedEvent
import com.minseoklim.cqrspractice.event.UserContactAddedEvent
import com.minseoklim.cqrspractice.event.UserContactRemovedEvent
import com.minseoklim.cqrspractice.event.UserCreatedEvent
import com.minseoklim.cqrspractice.event.UserEvent
import com.minseoklim.cqrspractice.repository.EventStore
import com.minseoklim.cqrspractice.util.UserUtility
import org.springframework.stereotype.Component

@Component
class UserAggregate(
    private val eventStore: EventStore
) {
    fun handleCreateUserCommand(command: CreateUserCommand): List<Event> {
        val event = UserCreatedEvent(command.userId, command.name)
        eventStore.addUserEvent(event)
        return listOf(event)
    }

    fun handleUpdateUserCommand(command: UpdateUserCommand): List<Event> {
        val user = UserUtility.recreateUserState(eventStore, command.userId)
        val events = mutableListOf<UserEvent>()

        val userContactAddedEvents = command.contacts
            .filter { it !in user.contacts }
            .map { UserContactAddedEvent(command.userId, it.type, it.detail) }
        events.addAll(userContactAddedEvents)

        val userContactRemovedEvents = user.contacts
            .filter { it !in command.contacts }
            .map { UserContactRemovedEvent(command.userId, it.type, it.detail) }
        events.addAll(userContactRemovedEvents)

        val userAddressAddedEvents = command.addresses
            .filter { it !in user.addresses }
            .map { UserAddressAddedEvent(command.userId, it.city, it.state, it.postalCode) }
        events.addAll(userAddressAddedEvents)

        val userAddressRemovedEvents = user.addresses
            .filter { it !in command.addresses }
            .map { UserAddressRemovedEvent(command.userId, it.city, it.state, it.postalCode) }
        events.addAll(userAddressRemovedEvents)

        eventStore.addUserEvents(events)

        return events
    }
}
