package com.minseoklim.designpattern.decorator

class ZipOut(
    delegate: FileOut
) : Decorator(delegate) {
    override fun write(data: String) {
        val zippedData = zip(data)
        super.doDelegate(zippedData)
    }

    private fun zip(data: String): String {
        println("data is zipped.")
        return "zipped $data"
    }
}
