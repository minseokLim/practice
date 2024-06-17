package com.minseoklim.designpattern.mediator

class VideoListUI(
    private val videoMediator: VideoMediator,
) {
    private val videoList: MutableList<VideoInfo> = mutableListOf()

    fun onSelectedItem(selectedIdx: Int) {
        videoMediator.select(videoList[selectedIdx].file)
    }

    fun addVideo(videoInfo: VideoInfo) {
        videoList.add(videoInfo)
    }
}
