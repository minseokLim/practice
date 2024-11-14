package com.minseoklim.toyproject2024.feed.ui.dto

import com.minseoklim.toyproject2024.feed.application.dto.QueryFeedOutput
import com.minseoklim.toyproject2024.feed.domain.model.FeedType

data class QueryFeedResponse private constructor(
    val id: Long,
    val title: String,
    val content: String,
    val feedType: FeedType,
    val files: List<AttachedFileResponse>,
    val youtubeLinkUrl: String?,
    val member: MemberResponse
) {
    companion object {
        fun from(output: QueryFeedOutput): QueryFeedResponse {
            return QueryFeedResponse(
                id = output.id,
                title = output.title,
                content = output.content,
                feedType = output.feedType,
                files = output.files.map { AttachedFileResponse.from(it) },
                youtubeLinkUrl = output.youtubeLinkUrl,
                member = MemberResponse.from(output.member)
            )
        }
    }

    data class AttachedFileResponse private constructor(
        val id: Long,
        val fileName: String,
        val fileLinkUrl: String
    ) {
        companion object {
            fun from(attachedFile: QueryFeedOutput.AttachedFileOutput): AttachedFileResponse {
                return AttachedFileResponse(
                    id = attachedFile.id,
                    fileName = attachedFile.fileName,
                    fileLinkUrl = attachedFile.fileLinkUrl
                )
            }
        }
    }

    data class MemberResponse private constructor(
        val id: Long,
        val name: String
    ) {
        companion object {
            fun from(member: QueryFeedOutput.MemberOutput): MemberResponse {
                return MemberResponse(
                    id = member.id,
                    name = member.name
                )
            }
        }
    }
}
