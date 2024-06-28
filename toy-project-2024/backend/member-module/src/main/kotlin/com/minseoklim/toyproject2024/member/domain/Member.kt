package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.auth.domain.Role
import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["login_id"])])
class Member(
    loginId: String?,
    password: String?,
    name: String,
    email: String,
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

    var email: Email = Email(email)
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

    constructor(
        name: String,
        email: String,
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
        memberRoles.addRole(role)
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
    }

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Member

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()
}
