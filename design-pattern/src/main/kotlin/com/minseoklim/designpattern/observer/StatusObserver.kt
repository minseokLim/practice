package com.minseoklim.designpattern.observer

interface StatusObserver {
    fun onAbnormalStatus(status: Status)
}
