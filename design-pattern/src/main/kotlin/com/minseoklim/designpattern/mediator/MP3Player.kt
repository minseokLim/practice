package com.minseoklim.designpattern.mediator

import java.io.File

class MP3Player {
    fun play(file: File) {
        println("Playing MP3: $file")
    }

    fun volumeChanged(volume: Int) {
        println("Volume changed to $volume on MP3 player")
    }
}
