package com.example.finalproject.retrofit

import com.google.gson.annotations.SerializedName

class GetResTrainPo (
    // 성공했을 때
    @SerializedName("errorMessage")            val errorMessage: Err,
    @SerializedName("realtimePositionList")     val realtimePositionList: List<TrainPoList>,
    // 실패했을 때
    @SerializedName("status")                  val status: Int,
    @SerializedName("code")                    val code: String,
    @SerializedName("message")                 val message: String,
    @SerializedName("link")                    val link: String,
    @SerializedName("developerMessage")        val developerMessage: String,
    @SerializedName("total")                   val total: Int
)

data class TrainPoList(
    //  @SerializedName("beginRow")                   val beginRow: String,
    // @SerializedName("endRow")                   val endRow: String,
    // @SerializedName("curPage")                   val curPage: String,
    // @SerializedName("pageRow")                   val pageRow: String,
    @SerializedName("totalCount")       val totalCount: Int, // 총 열차 개수
    @SerializedName("rowNum")           val rowNum: Int,
    @SerializedName("selectedCount")    val selectedCount: Int,
    @SerializedName("subwayId")         val subwayId: Int, // 호선 id
    @SerializedName("subwayNm")         val subwayNm: String, // 호선 이름
    @SerializedName("statnId")          val statnId: Int,     // 지하철역 id
    @SerializedName("statnNm")          val statnNm: String,  // 지하철역 이름
    @SerializedName("trainNo")          val trainNo: Int,     // 열차 번호
    @SerializedName("lastRecptnDt")     val lastRecptnDt: Int,// 최종 수신 날짜
    @SerializedName("recptnDt")         val recptnDt: String, //
    @SerializedName("updnLine")         val updnLine: Int,    // 상하행 구분
    @SerializedName("statnTid")         val statnTid: Int,    // 종착역 id
    @SerializedName("statnTnm")         val statnTnm: String, // 종착역 이름
    @SerializedName("trainSttus")       val trainSttus: Int,  // 열차 상태
    @SerializedName("directAt")         val directAt: Int,    // 급행 여부
    @SerializedName("lstcarAt")         val lstcarAt: Int,    // 막차 여부
)
