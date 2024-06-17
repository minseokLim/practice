package com.minseoklim.designpattern.observer

class StatusEmailSender : StatusObserver {
    override fun onAbnormalStatus(status: Status) {
        println("email is sent. status: $status")
    }
}
