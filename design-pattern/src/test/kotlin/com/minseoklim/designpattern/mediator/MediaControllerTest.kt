package com.minseoklim.designpattern.mediator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class MediaControllerTest {

    @Test
    fun volumeChanged() {
        // given
        val mediaController = MediaController()
        VideoMediator(VideoPlayer(), TitleUI(), mediaController)

        // when, then
        assertDoesNotThrow { mediaController.volumeChanged(10) }
    }
}
