package com.minseoklim.designpattern.mediator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.io.File

class VideoListUITest {

    @Test
    fun onSelectedItem() {
        // given
        val videoMediator = VideoMediator(VideoPlayer(), TitleUI(), MediaController())
        val videoListUI = VideoListUI(videoMediator)
        val videoInfo = VideoInfo("test", File.createTempFile("test", ".mp4"))
        videoListUI.addVideo(videoInfo)

        // when, then
        assertDoesNotThrow { videoListUI.onSelectedItem(0) }
    }
}
