package com.minseoklim.toyproject2024.common.util

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import java.io.InputStream

@Primary
@Component
class DummyUploadFileUtil : UploadFileUtil {
    override fun upload(
        uploadFileName: String,
        inputStream: InputStream
    ): String {
        // do nothing
        return "dummy-url"
    }
}
