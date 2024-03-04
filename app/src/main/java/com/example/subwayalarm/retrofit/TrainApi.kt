package com.example.finalproject.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TrainApi {

    // 실시간 역 도착 정보
    @Headers("Content-Type: application/json")
    @GET("{trainid}")
    fun getTrain(@Path("trainid") trainid: String) : Call<GetResTrain>

    // 실시간 열차 위치 정보. 위치 좌표데이터 아니고 해당 열차가 어떤 역에 도착인지 출발인지 간단한 데이터임
    @Headers("Content-Type: application/json")
    @GET("{line}")
    fun getTrainPositon(@Path("line") trainid: String) : Call<GetResTrainPo>

}