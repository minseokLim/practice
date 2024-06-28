package com.minseoklim.toyproject2024.member.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MemberRepository : JpaRepository<Member, Int> {
    fun findByLoginIdValue(loginId: String): Member?
    fun countByLoginIdValue(loginId: String): Int

    @Query(
        """
        SELECT m FROM Member m JOIN m.socialLinks.values s
        WHERE s.socialType = :socialType AND s.socialId.value = :socialId
    """
    )
    fun findBySocialTypeAndSocialId(socialType: SocialType, socialId: String): Member?
}
