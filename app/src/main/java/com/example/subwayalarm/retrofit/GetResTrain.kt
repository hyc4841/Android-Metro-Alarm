package com.example.finalproject.retrofit

import com.google.gson.annotations.SerializedName

class GetResTrain(
    // 열차 정보 가져오기 성공했을 때
    @SerializedName("errorMessage")            val errorMessage: Err,
    @SerializedName("realtimeArrivalList")     val realtimeArrivalList: List<TrainList>,
    // 에러 났을 때
    @SerializedName("status")                  val status: Int,
    @SerializedName("code")                    val code: String,
    @SerializedName("message")                 val message: String,
    @SerializedName("link")                    val link: String,
    @SerializedName("developerMessage")        val developerMessage: String,
    @SerializedName("total")                   val total: Int
)

data class Err(
    @SerializedName("status")                  val status: Int,
    @SerializedName("code")                    val code: String,
    @SerializedName("message")                 val message: String,
    @SerializedName("link")                    val link: String,
    @SerializedName("developerMessage")        val developerMessage: String,
    @SerializedName("total")                   val total: Int
)

data class TrainList(
    // @SerializedName("beginRow")     val beginRow: String,
    // @SerializedName("endRow")       val endRow: String,
    // @SerializedName("curPage")      val curPage: String,
    // @SerializedName("pageRow")      val pageRow: Int,
    @SerializedName("totalCount")   val totalCount: Int, // 현재 노선에서 운행중인 열차 갯수
    @SerializedName("rowNum")       val rowNum: Int,
    // @SerializedName("selectedCount")val selectedCount: Int,
    @SerializedName("subwayId")     val subwayId: Int, // 지하철호선ID : 1001:1호선, 1002:2호선, 1003:3호선 이런식
    // @SerializedName("subwayNm")     val subwayNm: String,
    @SerializedName("updnLine")     val updnLine: String, // 상하행선구분 : (0 : 상행/내선, 1 : 하행/외선)
    @SerializedName("trainLineNm")  val trainLineNm: String, // 도착지방면 : (성수행(목적지역) - 구로디지털단지방면(다음역))
    @SerializedName("subwayHeading")val subwayHeading: String, // ??
    @SerializedName("statnFid")    val statnFid: Int, // 이전지하철역ID
    @SerializedName("statnTid")    val statnTid: Int, // 다음지하철역ID
    @SerializedName("statnId")     val statnId: Int, // 지하철역ID
    @SerializedName("statnNm")     val statnNm: String, //지하철역명
    @SerializedName("trnsitCo")    val trnsitCo: Int, // 환승노선수
    @SerializedName("ordkey")      val ordkey: String, // 도착예정열차순번 : 상하행코드(한자리)/순번(한자리)/첫번째 도착예정 정류장 - 현재 정류장(3자리)/목적지 정류장/급행여부(한자리) => 01003성수0
    @SerializedName("subwayList")  val subwayList: String,// 환승호선ID : 1002, 1007 등 환승호선ID)
    @SerializedName("statnList")   val statnList: String, // 환승지하철역ID : 1002000233, 1007000000
    @SerializedName("btrainSttus") val btrainSttus: String, // 열차종류 : (급행,ITX,일반,특급)
    @SerializedName("barvlDt")     val barvlDt: Int,  // 열차도착예정시간(단위:초)
    @SerializedName("btrainNo")    val btrainNo: Int, // 열차번호 : 현재운행하고 있는 호선별 열차번호
    @SerializedName("bstatnId")    val bstatnId: Int, // 종착지하철역ID
    @SerializedName("bstatnNm")    val bstatnNm: String, // 종착지하철역명
    @SerializedName("recptnDt")    val recptnDt: String, // 열차도착정보를 생성한 시각
    @SerializedName("arvlMsg2")    val arvlMsg2: String, // 첫번째도착메세지 : 도착, 출발 , 진입 등
    @SerializedName("arvlMsg3")    val arvlMsg3: String, // 두번째도착메세지 : 현재 도착 예정 혹은 출발 중인 역 이름
    @SerializedName("arvlCd")      val arvlCd: Int // 도착코드 : 0:진입, 1:도착, 2:출발, 3:전역출발, 4:전역진입, 5:전역도착, 99:운행중
)