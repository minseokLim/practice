package com.minseoklim.designpattern.decorator

class EncryptionOut(
    delegate: FileOut
) : Decorator(delegate) {
    override fun write(data: String) {
        val encryptedData = encrypt(data)
        super.doDelegate(encryptedData)
    }

    private fun encrypt(data: String): String {
        println("data is encrypted.")
        return "encrypted $data"
    }
}
