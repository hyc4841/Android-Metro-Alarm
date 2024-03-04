package com.example.finalproject

object LastTrain {
    val lastTrain = mutableMapOf<Int, String>(
        1 to "막차", 0 to "아님"
    )

    fun SearchLast(i: Int): String? {
        return lastTrain[i]
    }
}