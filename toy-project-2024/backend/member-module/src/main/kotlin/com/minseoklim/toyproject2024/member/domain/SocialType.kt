package com.minseoklim.toyproject2024.member.domain

enum class SocialType {
    GOOGLE {
        override fun toMemberEntity(attributes: Map<String, Any>): Member {
            return Member(
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                socialType = this,
                socialId = extractSocialId(attributes)
            )
        }

        override fun extractSocialId(attributes: Map<String, Any>): String {
            return attributes["sub"] as String
        }
    };

    abstract fun toMemberEntity(attributes: Map<String, Any>): Member
    abstract fun extractSocialId(attributes: Map<String, Any>): String
}
