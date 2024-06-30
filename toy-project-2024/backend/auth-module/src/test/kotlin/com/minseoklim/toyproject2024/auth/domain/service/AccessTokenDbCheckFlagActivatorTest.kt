package com.minseoklim.toyproject2024.auth.domain.service

import com.minseoklim.toyproject2024.auth.domain.model.AccessTokenDbCheckFlag
import com.minseoklim.toyproject2024.auth.domain.repository.AccessTokenDbCheckFlagRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AccessTokenDbCheckFlagActivatorTest {
    private lateinit var accessTokenDbCheckFlagRepository: AccessTokenDbCheckFlagRepository
    private lateinit var accessTokenDbCheckFlagActivator: AccessTokenDbCheckFlagActivator
    private val accessTokenDbCheckInMilliseconds = 1000L

    @BeforeEach
    fun setUp() {
        accessTokenDbCheckFlagRepository = mockk()
        accessTokenDbCheckFlagActivator =
            AccessTokenDbCheckFlagActivator(accessTokenDbCheckFlagRepository, accessTokenDbCheckInMilliseconds)
    }

    @Test
    fun activateAccessTokenDbCheckFlag() {
        // given
        val memberId = 1
        every {
            accessTokenDbCheckFlagRepository.save(any())
        } returns AccessTokenDbCheckFlag(
            memberId,
            accessTokenDbCheckInMilliseconds
        )

        // when
        accessTokenDbCheckFlagActivator.activateAccessTokenDbCheckFlag(memberId)

        // then
        verify {
            accessTokenDbCheckFlagRepository.save(
                AccessTokenDbCheckFlag(
                    memberId,
                    accessTokenDbCheckInMilliseconds
                )
            )
        }
    }
}
