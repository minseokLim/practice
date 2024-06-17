package com.minseoklim.designpattern.proxy

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class ListUITest {

    @Test
    fun onScroll() {
        // given
        val images = listOf(
            ProxyImage("image1.jpg"),
            ProxyImage("image2.jpg"),
            ProxyImage("image3.jpg"),
            ProxyImage("image4.jpg"),
            ProxyImage("image5.jpg")
        )
        val listUI = ListUI(images)

        // when, then
        assertDoesNotThrow { listUI.onScroll(0, 4) }
    }
}
