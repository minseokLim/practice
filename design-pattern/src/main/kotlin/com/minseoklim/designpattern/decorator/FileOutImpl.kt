package com.minseoklim.designpattern.decorator

class FileOutImpl : FileOut {
    override fun write(data: String) {
        println("$data is written to file.")
    }
}
