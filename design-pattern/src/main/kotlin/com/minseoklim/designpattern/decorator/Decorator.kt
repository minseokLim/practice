package com.minseoklim.designpattern.decorator

abstract class Decorator(
    private val delegate: FileOut
) : FileOut {
    fun doDelegate(data: String) {
        delegate.write(data)
    }
}
