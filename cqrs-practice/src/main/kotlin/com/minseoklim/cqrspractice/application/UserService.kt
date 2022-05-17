package com.minseoklim.cqrspractice.application

import com.minseoklim.cqrspractice.command.CreateUserCommand
import com.minseoklim.cqrspractice.command.UpdateUserCommand
import com.minseoklim.cqrspractice.domain.Address
import com.minseoklim.cqrspractice.domain.Contact
import com.minseoklim.cqrspractice.domain.UserAggregate
import com.minseoklim.cqrspractice.domain.UserProjection
import com.minseoklim.cqrspractice.query.AddressByStateQuery
import com.minseoklim.cqrspractice.query.ContactByTypeQuery
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(
    private val userAggregate: UserAggregate,
    private val userProjection: UserProjection
) {
    fun createUser(userId: String, name: String) {
        userAggregate.handleCreateUserCommand(CreateUserCommand(userId, name))
    }

    fun updateUser(userId: String, contacts: Set<Contact>, addresses: Set<Address>) {
        userAggregate.handleUpdateUserCommand(UpdateUserCommand(userId, contacts, addresses))
    }

    fun getContactsByType(userId: String, type: String): Optional<Set<Contact>> {
        return userProjection.handleContactByTypeQuery(ContactByTypeQuery(userId, type))
    }

    fun getAddressesByState(userId: String, state: String): Optional<Set<Address>> {
        return userProjection.handleAddressByStateQuery(AddressByStateQuery(userId, state))
    }
}
