package com.minseoklim.designpattern.observer

class StatusChecker : StatusSubject() {
    fun check() {
        val status = loadStatus()

        if (status.isAbnormal()) {
            super.notifyObservers(status)
        }
    }

    private fun loadStatus(): Status {
        return Status.ABNORMAL
    }
}
