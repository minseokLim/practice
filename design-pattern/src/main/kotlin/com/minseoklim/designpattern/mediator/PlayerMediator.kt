package com.minseoklim.designpattern.mediator

import java.io.File

abstract class PlayerMediator(
    private val titleUI: TitleUI,
    mediaController: MediaController
) : ControllerObserver {
    init {
        mediaController.addObserver(this)
    }

    open fun select(file: File) {
        titleUI.setTitle(file.name)
    }
}
