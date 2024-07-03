package com.minseoklim.toyproject2024.member.domain.model

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
    },
    KAKAO {
        override fun toMemberEntity(attributes: Map<String, Any>): Member {
            return Member(
                name = (attributes["properties"] as Map<*, *>)["nickname"] as String,
                socialType = this,
                socialId = extractSocialId(attributes)
            )
        }

        override fun extractSocialId(attributes: Map<String, Any>): String {
            return attributes["id"].toString()
        }
    },
    NAVER {
        override fun toMemberEntity(attributes: Map<String, Any>): Member {
            return Member(
                name = (attributes["response"] as Map<*, *>)["name"] as String,
                email = (attributes["response"] as Map<*, *>)["email"] as String,
                socialType = this,
                socialId = extractSocialId(attributes)
            )
        }

        override fun extractSocialId(attributes: Map<String, Any>): String {
            return (attributes["response"] as Map<*, *>)["id"] as String
        }
    };

    abstract fun toMemberEntity(attributes: Map<String, Any>): Member
    abstract fun extractSocialId(attributes: Map<String, Any>): String
}
