package com.minseoklim.cqrspractice.application

import com.minseoklim.cqrspractice.domain.Address
import com.minseoklim.cqrspractice.domain.Contact
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserServiceTest {
    @Autowired
    private lateinit var userService: UserService

    @Test
    fun 통합테스트() {
        val userId = "minseoklim"

        사용자_생성_요청(userId, "임민석")

        val contacts = setOf(Contact("휴대폰", "01012345678"), Contact("휴대폰", "01111111111"))
        val addresses = setOf(Address("부천", "경기도", "1234"), Address("파주", "경기도", "0123"))
        사용자_수정_요청(userId, contacts, addresses)

        val newContacts = setOf(Contact("휴대폰", "01077777777"), Contact("휴대폰", "01111111111"))
        val newAddresses = setOf(Address("부천", "경기도", "1234"), Address("화성", "경기도", "3232"))
        사용자_수정_요청(userId, newContacts, newAddresses)

        사용자_수정됨(userId, "휴대폰", newContacts, "경기도", newAddresses)
    }

    private fun 사용자_생성_요청(userId: String, name: String) {
        userService.createUser(userId, name)
    }

    private fun 사용자_수정_요청(userId: String, contacts: Set<Contact>, addresses: Set<Address>) {
        userService.updateUser(userId, contacts, addresses)
    }

    private fun 사용자_수정됨(
        userId: String,
        contactType: String,
        newContacts: Set<Contact>,
        state: String,
        newAddresses: Set<Address>
    ) {
        // 읽기 저장소 반영이 비동기로 되다보니 좀 기다려야 확인을 할 수 있음
        Thread.sleep(5000)

        val foundContacts = userService.getContactsByType(userId, contactType).get()
        assert(foundContacts == newContacts)

        val foundAddresses = userService.getAddressesByState(userId, state).get()
        assert(foundAddresses == newAddresses)
    }
}
