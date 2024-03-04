package com.example.finalproject

object TrainSttus {
    val trainSttus = mutableMapOf<Int, String>(
        0 to "진입", 1 to "도착", 2 to "출발",
        3 to " 전역 출발", 4 to " 전역 진입",
        5 to "전역 도착" , 99 to "운행중"
    )

    fun SearchSttus(sttus: Int): String? {
        return trainSttus[sttus]
    }
}