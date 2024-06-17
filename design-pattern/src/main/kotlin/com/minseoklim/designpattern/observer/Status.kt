package com.minseoklim.designpattern.observer

enum class Status {
    NORMAL,
    ABNORMAL;

    fun isAbnormal(): Boolean {
        return this == ABNORMAL
    }
}
