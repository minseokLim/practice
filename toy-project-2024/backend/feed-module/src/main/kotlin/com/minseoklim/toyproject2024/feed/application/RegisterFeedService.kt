package com.minseoklim.toyproject2024.feed.application

import com.minseoklim.toyproject2024.common.util.UploadFileUtil
import com.minseoklim.toyproject2024.feed.domain.model.FeedAttachedFile
import com.minseoklim.toyproject2024.feed.domain.repository.FeedRepository
import com.minseoklim.toyproject2024.feed.dto.application.RegisterFeedInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
@Transactional
class RegisterFeedService(
    private val uploadFileUtil: UploadFileUtil,
    private val feedRepository: FeedRepository
) {
    fun register(
        memberId: Long,
        input: RegisterFeedInput,
        files: List<MultipartFile>
    ) {
        val attachedFiles = files.map {
            val fileName = checkNotNull(it.originalFilename)
            val extension = fileName.substringAfterLast(".")
            val uploadFileName = "${UUID.randomUUID()}.$extension"
            val fileLinkUrl = uploadFileUtil.upload(uploadFileName, it.inputStream)

            FeedAttachedFile(
                fileName = fileName,
                fileLinkUrl = fileLinkUrl
            )
        }

        feedRepository.save(input.toEntity(memberId, attachedFiles))
    }
}
