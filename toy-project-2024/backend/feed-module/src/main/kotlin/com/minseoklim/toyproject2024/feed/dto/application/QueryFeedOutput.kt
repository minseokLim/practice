package com.minseoklim.toyproject2024.feed.dto.application

import com.minseoklim.toyproject2024.feed.domain.model.Feed
import com.minseoklim.toyproject2024.feed.domain.model.FeedAttachedFile
import com.minseoklim.toyproject2024.feed.domain.model.FeedType
import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput

data class QueryFeedOutput private constructor(
    val id: Long,
    val title: String,
    val content: String,
    val feedType: FeedType,
    val files: List<AttachedFileOutput>,
    val youtubeLinkUrl: String?,
    val member: MemberOutput
) {
    companion object {
        fun of(
            feed: Feed,
            member: QueryMemberOutput
        ): QueryFeedOutput {
            return QueryFeedOutput(
                id = checkNotNull(feed.id),
                title = feed.title,
                content = feed.content,
                feedType = feed.feedType,
                files = feed.files.map { AttachedFileOutput.from(it) },
                youtubeLinkUrl = feed.youtubeLinkUrl,
                member = MemberOutput.from(member)
            )
        }
    }

    data class AttachedFileOutput private constructor(
        val id: Long,
        val fileName: String,
        val fileLinkUrl: String
    ) {
        companion object {
            fun from(attachedFile: FeedAttachedFile): AttachedFileOutput {
                return AttachedFileOutput(
                    id = checkNotNull(attachedFile.id),
                    fileName = attachedFile.fileName,
                    fileLinkUrl = attachedFile.fileLinkUrl
                )
            }
        }
    }

    data class MemberOutput private constructor(
        val id: Long,
        val name: String
    ) {
        companion object {
            fun from(member: QueryMemberOutput): MemberOutput {
                return MemberOutput(
                    id = member.id,
                    name = member.name
                )
            }
        }
    }
}
