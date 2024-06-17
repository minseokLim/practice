package com.minseoklim.designpattern.mediator

import java.io.File

class VideoMediator(
    private val videoPlayer: VideoPlayer,
    titleUI: TitleUI,
    mediaController: MediaController
) : PlayerMediator(titleUI, mediaController) {
    override fun select(file: File) {
        videoPlayer.play(file)
        super.select(file)
    }

    override fun volumeChanged(volume: Int) {
        videoPlayer.volumeChanged(volume)
    }
}
