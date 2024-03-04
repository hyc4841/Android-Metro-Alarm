package com.example.finalproject.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TrainRetrofitCreator {
    // 뒤에 역 이름만 붙여주면 된다.
    private const val BASE_URL = "http://swopenapi.seoul.go.kr/api/subway/6c754d696c68796337396c67676f65/json/realtimeStationArrival/0/20/"

    //Retrofit 객체 생성
    private val retrofit: Retrofit = Retrofit

        //레트로핏 빌더 생성 (생성자 호출)
        .Builder()

        //빌더 객체의 baseUrl 호출. 서버의 메인 URL 전달
        .baseUrl(BASE_URL)

        //gson 컨버터 연동
        .addConverterFactory(GsonConverterFactory.create())

        //Retrofit 객체 반환
        .build()

    //인터페이스 객체를 create에 넘겨 실제 구현체 생성
    val trainApi : TrainApi = retrofit.create(TrainApi::class.java)
}