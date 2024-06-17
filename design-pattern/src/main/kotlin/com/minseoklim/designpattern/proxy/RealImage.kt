package com.minseoklim.designpattern.proxy

class RealImage(
    private val filename: String
) : Image {
    override fun draw() {
        println("RealImage is drawn on $filename.")
    }
}
