package com.example.subwayalarm.ui.theme.subway

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.Traindata
import com.example.finalproject.retrofit.GetResTrain
import com.example.finalproject.retrofit.TrainApi
import com.example.finalproject.retrofit.TrainList
import com.example.finalproject.retrofit.TrainRetrofitCreator
import com.example.subwayalarm.R
import com.example.subwayalarm.model.Station
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class TrainViewModel : ViewModel() {
    private val trainApi: TrainApi = TrainRetrofitCreator.trainApi

    // 지하철 실시간 도착 데이터
    private val _SubwayData = mutableStateOf<List<Traindata>>(emptyList())
    val SubwayData: State<List<Traindata>> = _SubwayData

    // 도착역 리스트
    private val _DestinationList = mutableStateOf<List<Station>>(emptyList())
    val DestinationList: State<List<Station>> = _DestinationList

    // 출발역, 탑승 열차
    private val _DepartStation = mutableStateOf<String>("")
    val DepartStation: State<String> = _DepartStation
    private val _TrainNumData = mutableStateOf<String>("")
    val TrainNumData: State<String> = _TrainNumData

    // 임시 도착역
    private val _DesStationtmp = mutableStateOf<String>("")
    val DesStationtmp: State<String> = _DesStationtmp

    // 활성화된 도착역
    private val _ActDesStation = mutableStateOf<String>("")
    val ActDesStation: State<String> = _ActDesStation


    // 팝업2 플레그
    private val _Popup = mutableStateOf<Boolean>(false)
    val Popup: State<Boolean> = _Popup

    // 알람 활성화 플레그
    private val _isAlarmActive = mutableStateOf<Boolean>(false)
    val isAlarmActive: State<Boolean> = _isAlarmActive

    private val _findTrain = mutableStateOf<Boolean>(false)
    val findTrain: State<Boolean> = _findTrain


    // 이거 사용못함 최소 주기가 15분임
    /*
    fun scheduleBackgroundWork(context: Context) {
        // WorkManager를 사용하여 주기적인 작업 예약
        // 제약조건 설정. NetworkType, BatteryNotLow, RequiresCharging, DeviceIdle, StorageNotLow
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)  // 네트워크 연결이 필요한 경우
            .setRequiresBatteryNotLow(true)  // 배터리가 낮은 경우 실행하지 않음
            .build()

        val periodicWorkRequest = PeriodicWorkRequestBuilder<TrainTracker>(1, TimeUnit.SECONDS)
            // 만들어둔 제약조건을 넣어줌
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork( // 주기적 작업
            "MyBackgroundWork",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }
     */


    // 현재 도착지 확정
    fun setActDesStation() {
        Log.d(ContentValues.TAG, "popup2실행 누르면 실행됨 : setActDesStation")

        _ActDesStation.value = _DesStationtmp.value
        Log.d(ContentValues.TAG, "실제 도착지 설정 확인 : ${ActDesStation.value}")
    }


    // 이 카드가 지금 활성화된 카드입니까?
    fun isActiveCard(destination: String) : Color {
        if (destination == _ActDesStation.value) {
            _isAlarmActive.value = true
            return Color.Red
        }
        else {
            return Color.Gray
        }
    }

    // 팝업2 플레그 설정
    fun setPopupFlag(isPopupVisible2: Boolean) {
        _Popup.value = isPopupVisible2
    }

    // 도착역 임시 저장. 출발역, 탑승열차와 연계된 함수.
    fun setDestination(desStation: String) {
        _DesStationtmp.value = desStation
        Log.d(ContentValues.TAG, "도착역 설정 확인 : ${DesStationtmp.value}")
    }

     // 출발역과 열차번호 설정
    fun setDepart(departStation: String, trainNum: String) {
        _DepartStation.value = departStation
        _TrainNumData.value = trainNum

         Log.d(ContentValues.TAG, "popup2에서 확인버튼 누르면 실행됨 : setDepart ")
         Log.d(ContentValues.TAG, "출발역 설정 확인  : ${DepartStation.value}")
        Log.d(ContentValues.TAG, "열차 번호 설정 확인 : ${TrainNumData.value}")
    }

    fun setDestinationCard(stationName: String, line: String) {

        val image: Int = when (line) {
            "1호선" -> R.drawable.line_1
            "2호선" -> R.drawable.line_2
            "3호선" -> R.drawable.line_3
            "4호선" -> R.drawable.line_4
            "5호선" -> R.drawable.line_5
            "6호선" -> R.drawable.line_6
            "7호선" -> R.drawable.line_7
            "8호선" -> R.drawable.line_8
            "9호선" -> R.drawable.line_9
            "경의중앙선" -> R.drawable.line_gyeongui_jungang
            "공항철도" -> R.drawable.line_airport
            "경춘선" -> R.drawable.line_gyeongchun
            "수인분당선" -> R.drawable.line_suin_bundang
            "신분당선" -> R.drawable.line_shinbundang
            "경강선" -> R.drawable.line_gyeonggang
            "우이신설선" -> R.drawable.line_ui
            else -> R.drawable.line_seohae
        }

        val stationData = Station(stationName, line, image)
        _DestinationList.value = _DestinationList.value.toMutableList().apply { add(stationData) }
    }

    // 열차가 있는지 없는지 확인
    fun getSubwayData(stationName: String, trainNum: String) {
        val callTrain = trainApi.getTrain(stationName)
        viewModelScope.launch {
            callTrain.enqueue(object: Callback<GetResTrain> {
                override fun onResponse(call: Call<GetResTrain>, response: Response<GetResTrain>) {
                    if(response.isSuccessful) { // response check
                        val responseBody: GetResTrain? = response.body()
                        if (responseBody != null && responseBody.realtimeArrivalList != null) { // responseBody.realtimeArrivalList얘가 널이 항상 아니라고?

                            for( value in responseBody.realtimeArrivalList) {
                                if (trainNum == value.btrainNo.toString()) {
                                    Log.d(ContentValues.TAG, "열차 찾았다!! : ${value.btrainNo}")
                                    _findTrain.value = true
                                    return
                                }
                            }
                        }
                        else {
                            Log.d(ContentValues.TAG, "역이름을 확인하세요!!")
                        }
                    }
                    else { // !response.isSuccessful
                        Log.d(ContentValues.TAG, "response is Error!!")
                    }
                }
                override fun onFailure(call: Call<GetResTrain>, t: Throwable) {
                    Log.d(ContentValues.TAG, "Get 실패 : $t")
                }
            })
        }
    }

    fun DataPrepare(data : List<TrainList>) {
        _SubwayData.value = emptyList()
        for (i in data.indices) {
            val train = Traindata(data[i].subwayId, data[i].statnTid, data[i].statnNm, data[i].barvlDt,
                data[i].btrainNo, data[i].arvlMsg2, data[i].arvlMsg3, data[i].arvlCd,
                data[i].bstatnNm, data[i].recptnDt, i + 1
                )
            _SubwayData.value = _SubwayData.value + train
        }
    }









}