package com.minseoklim.designpattern.mediator

import java.io.File

class MP3Mediator(
    private val mp3Player: MP3Player,
    titleUI: TitleUI,
    mediaController: MediaController,
) : PlayerMediator(titleUI, mediaController) {
    override fun select(file: File) {
        mp3Player.play(file)
        super.select(file)
    }

    override fun volumeChanged(volume: Int) {
        mp3Player.volumeChanged(volume)
    }
}
