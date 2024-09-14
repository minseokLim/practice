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
                name = extractProperties(attributes)["nickname"] as String,
                socialType = this,
                socialId = extractSocialId(attributes)
            )
        }

        override fun extractSocialId(attributes: Map<String, Any>): String {
            return attributes["id"].toString()
        }

        private fun extractProperties(attributes: Map<String, Any>): Map<*, *> {
            return attributes["properties"] as Map<*, *>
        }
    },
    NAVER {
        override fun toMemberEntity(attributes: Map<String, Any>): Member {
            return Member(
                name = extractResponse(attributes)["name"] as String,
                email = extractResponse(attributes)["email"] as String,
                socialType = this,
                socialId = extractSocialId(attributes)
            )
        }

        override fun extractSocialId(attributes: Map<String, Any>): String {
            return extractResponse(attributes)["id"] as String
        }

        private fun extractResponse(attributes: Map<String, Any>): Map<*, *> {
            return attributes["response"] as Map<*, *>
        }
    };

    abstract fun toMemberEntity(attributes: Map<String, Any>): Member

    abstract fun extractSocialId(attributes: Map<String, Any>): String
}
