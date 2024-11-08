package com.minseoklim.toyproject2024.common.util

import java.io.InputStream

interface UploadFileUtil {
    /**
     * @return The URL of the uploaded file.
     */
    fun upload(
        uploadFileName: String,
        inputStream: InputStream
    ): String
}
