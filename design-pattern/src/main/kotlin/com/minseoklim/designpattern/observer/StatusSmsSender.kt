package com.minseoklim.designpattern.observer

class StatusSmsSender : StatusObserver {
    override fun onAbnormalStatus(status: Status) {
        println("sms is sent. status: $status")
    }
}
