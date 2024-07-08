package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.util.CommonUtil.entityEmbeddableEquals
import com.minseoklim.toyproject2024.common.util.CommonUtil.entityHashCode
import com.minseoklim.toyproject2024.common.util.EventPublisher
import com.minseoklim.toyproject2024.member.event.MemberDeletedEvent
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import jakarta.persistence.Version
import org.springframework.dao.OptimisticLockingFailureException

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["login_id"])])
class Member(
    loginId: String?,
    password: String?,
    name: String,
    email: String?,
    socialType: SocialType? = null,
    socialId: String? = null
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    val loginId: LoginId? = loginId?.let { LoginId(loginId) }

    var password: Password? = password?.let { Password(password) }
        protected set

    var name: Name = Name(name)
        protected set

    var email: Email? = email?.let { Email(email) }
        protected set

    val memberRoles: MemberRoles = MemberRoles().apply { addRole(Role.MEMBER) }

    val socialLinks: SocialLinks = SocialLinks().apply {
        socialType?.let { socialType ->
            socialId?.let { socialId ->
                addSocialLink(socialType, socialId)
            }
        }
    }

    var isDeleted: Boolean = false
        protected set

    @Version
    val version: Long? = null

    constructor(
        name: String,
        email: String? = null,
        socialType: SocialType,
        socialId: String
    ) : this(
        loginId = null,
        password = null,
        name = name,
        email = email,
        socialType = socialType,
        socialId = socialId
    )

    fun addRole(role: Role) {
        if (memberRoles.getRoles().contains(role)) {
            throw BadRequestException("ROLE_DUPLICATED", "이미 등록된 권한입니다.")
        }
        memberRoles.addRole(role)
    }

    fun deleteRole(role: Role) {
        memberRoles.deleteRole(role)
        if (memberRoles.getRoles().isEmpty()) {
            throw BadRequestException("ROLE_REQUIRED", "최소 1개 이상의 권한은 필수입니다.")
        }
    }

    fun getRoles(): Set<Role> {
        return memberRoles.getRoles()
    }

    fun addSocialLink(socialType: SocialType, socialId: String) {
        if (socialLinks.getSocialLinks().map { it.socialType }.contains(socialType)) {
            throw BadRequestException("SOCIAL_LINK_DUPLICATED", "이미 연동된 소셜 계정입니다.")
        }
        socialLinks.addSocialLink(socialType, socialId)
    }

    fun deleteSocialLink(socialType: SocialType) {
        socialLinks.deleteSocialLink(socialType)
        if (loginId == null && socialLinks.getSocialLinks().isEmpty()) {
            throw BadRequestException("SOCIAL_LINK_REQUIRED", "로그인 ID가 없는 경우, 1개 이상의 소셜 계정 연동은 필수입니다.")
        }
    }

    fun getSocialLinks(): Set<SocialLink> {
        return socialLinks.getSocialLinks()
    }

    fun update(other: Member) {
        password = other.password
        name = other.name
        email = other.email
    }

    fun delete() {
        isDeleted = true
        EventPublisher.publish(MemberDeletedEvent(this, id!!))
    }

    fun validateVersion(version: Long) {
        if (this.version != version) {
            throw OptimisticLockingFailureException("요청한 version과 현재 version이 일치하지 않습니다.")
        }
    }

    final override fun equals(other: Any?): Boolean {
        return this.entityEmbeddableEquals(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.entityHashCode()
}
