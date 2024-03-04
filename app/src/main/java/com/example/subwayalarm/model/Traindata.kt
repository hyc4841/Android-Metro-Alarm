package com.example.finalproject

data class Traindata(
    val subwayId: Int, // 호선
    val statnTid: Int, // 다음 지하쳘역
    val statnNm: String, // 현재 지하철열
    val barvlDt: Int, // 열차 도착 예정시간
    var btrainNo: Int, // 열차 번호
    val arvlMsg2: String, // 도착 메시지1
    val arvlMsg3: String, // 도착 메시지2
    val arvlCd: Int, // 도착 코드
    val bstatnNm: String, // 종착역
    val recptnDt: String, // 열차 도착정보 생성시각
    val index: Int
    //val statnFid: Int,
    //val statnTid: Int,
    //val statnId: Int,
    //val statnNm: String,
    //val trnsitCo: Int,
    //val ordkey: String,
    //val subwayList: String,
    //val statnList: String,
    //val btrainSttus: String,
    //val barvlDt: Int,
    // val btrainNo: Int,
    //val bstatnId: Int,
    //val bstatnNm: String,
    //val recptnDt: String,
    //val arvlMsg2: String,
    //val arvlMsg3: String,
    //val arvlCd: Int
)
