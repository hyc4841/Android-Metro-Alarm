package com.example.finalproject

data class TrainPodata (
    val trainNo: Int,      // 열차 번호
    val line: String,       // 호선
    val stationNm: String, // 다음역 이름
    val sttus: String,      // 열차 상태
    val direct: String,     // 급행 여부
    val lst: String         // 막차 여부
)
