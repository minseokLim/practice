package com.minseoklim.designpattern.proxy

class ProxyImage(
    private val filename: String
) : Image {
    private lateinit var realImage: RealImage

    override fun draw() {
        if (!::realImage.isInitialized) {
            realImage = RealImage(filename)
        }
        realImage.draw()
    }
}
