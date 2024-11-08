package com.minseoklim.toyproject2024.common.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.io.InputStream

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
        val file = File("$uploadPath/$uploadFileName")
        file.outputStream().use { it.write(inputStream.readBytes()) }
        return "$baseUrl/$uploadFileName"
    }
}
