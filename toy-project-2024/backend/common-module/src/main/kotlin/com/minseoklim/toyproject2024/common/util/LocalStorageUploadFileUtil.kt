package com.minseoklim.toyproject2024.common.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths

@Component
class LocalStorageUploadFileUtil(
    @Value("\${upload.path}")
    private val uploadPath: String,
    @Value("\${upload.base-url}")
    private val baseUrl: String
) : UploadFileUtil {
    override fun upload(
        uploadFileName: String,
        inputStream: InputStream
    ): String {
        val uploadDir = Paths.get(uploadPath)
        if (Files.notExists(uploadDir)) {
            Files.createDirectories(uploadDir)
        }
        Files.copy(inputStream, uploadDir.resolve(uploadFileName))
        return "$baseUrl/$uploadFileName"
    }
}
