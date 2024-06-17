package com.minseoklim.designpattern.mediator

import java.io.File

class VideoPlayer {
    fun play(videoFile: File) {
        println("Playing video: ${videoFile.name}")
    }

    fun volumeChanged(volume: Int) {
        println("Volume changed to $volume on video player")
    }
}
