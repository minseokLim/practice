package com.minseoklim.toyproject2024.member.domain.model

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority {
    ADMIN,
    MEMBER;

    override fun getAuthority(): String {
        return ROLE_PREFIX + name
    }

    companion object {
        const val ROLE_PREFIX = "ROLE_"

        fun of(role: String): Role {
            return valueOf(role.substring(ROLE_PREFIX.length))
        }
    }
}
