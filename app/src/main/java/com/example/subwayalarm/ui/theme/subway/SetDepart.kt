package com.example.subwayalarm.ui.theme.subway

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.work.impl.utils.ForceStopRunnable
import com.example.finalproject.LineList
import com.example.finalproject.TrainIdNmList
import com.example.finalproject.TrainSttus
import com.example.finalproject.retrofit.GetResTrain
import com.example.finalproject.retrofit.TrainApi
import com.example.finalproject.retrofit.TrainRetrofitCreator
import com.example.subwayalarm.Alarm
import com.example.subwayalarm.model.Station
import com.example.subwayalarm.model.stations
import kotlinx.coroutines.CompletableDeferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetDepart(
    navController: NavController,
    trainViewModel: TrainViewModel,
    context: Context
) {
    val (searchTerm, updateSearchTerm) = remember { mutableStateOf("") }
    val stname = remember { mutableStateOf("") } // 출발역
    val lineNm = remember { mutableStateOf("") } // 열차 라인

    // 팝업창
    var isPopupVisible1 by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 역 이름을 입력받는 창
        TextField(
            value = searchTerm,
            onValueChange = updateSearchTerm,
            label = { Text("역 이름을 입력하세요") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Button( // 버튼을 누르면 목적지 설정창으로 이동한다.
            onClick = { navController.navigate(route = Alarm.route) } // popBackStack()사용하면 데이터 날아감
        ) {
            Text(text = "뒤로가기")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val filter = getStation(searchTerm, stations)
            if (searchTerm != "") { // 아무것도 입력하지 않은 초기상태는 lazyColumn의 아이템이 보이지 않도록 함.
                items(
                    items = filter, // items 함수에 key를 적어주지 않아도 알아서 내부적으로 고유 키값을 생성해줌.
                ) { item ->
                    DestinationCard( // 도착역을 선택을 하면
                        drawable = item.lineImage,
                        text = item.stationNm,
                        modifier = Modifier
                            .padding(2.dp)
                            .clickable( // 카드를 누르면 팝업창이 열리도록
                                onClick = {
                                    stname.value = item.stationNm // 출발역
                                    lineNm.value = item.line
                                    isPopupVisible1 = true// isPopupVisible1이 true가 되면서 팝업창 열림
                                }
                            )
                    )
                }
            }
        }

        // 팝업 창이 열린 경우 Popupp1 호출
        if (isPopupVisible1) {
            Popupp1(
                line = lineNm.value,
                stationName = stname.value, // 출발역을 입력 받는다.
                onClosePopup = { isPopupVisible1 = false },
                navController = navController,
                trainViewModel = trainViewModel
            )
        }

        val isPopupp2 = trainViewModel.Popup.value // Popupp2를 호출하기 위한 변수를 뷰 모델이 미리 선언해 둠

        if (isPopupp2) {
            Popupp2(
                line = lineNm.value, // 열차 라인
                departStation = stname.value, // 출발역 이름
                onClosePopup = { trainViewModel.setPopupFlag(false) }, // 팝업 닫히는거
                navController = navController,
                trainViewModel = trainViewModel,
                context = context
            )

        }

    }

}

@Composable
fun Popupp1( // 선택한 역이 맞는지 확인 팝업
    // 필요한 데이터가 무엇이 있을까?
    // 여기는 그냥 확인만 하는 곳이니까
    stationName: String,
    line: String,
    onClosePopup: () -> Unit,
    navController: NavController,
    trainViewModel: TrainViewModel
) {

    AlertDialog(
        onDismissRequest = { onClosePopup() },
        title = { Text(
            text = "${stationName}역을 출발역으로 설정하시겠습니까?",
            fontSize = 20.sp
        ) },
        confirmButton = {
            // 확인버튼
            Button(
                onClick = {
                    onClosePopup()
                    trainViewModel.setPopupFlag(true) //
                }
            ) {
                Text("확인")
            }

            // 취소 번튼
            Button(
                onClick = {
                    onClosePopup()
                }
            ) {
                Text("취소")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Popupp2( // 탑승 열차 입력 팝업..
    departStation: String, // 출발역 departStation과 line은 Popupp1과 관계 없음
    line: String, // 라인이 필요할까? 호선
    onClosePopup: () -> Unit,
    navController: NavController,
    trainViewModel: TrainViewModel,
    context: Context
    ) {
    var trainNum by remember { mutableStateOf("") } // 텍스트 필드 열차번호 입력 감지용

    // 팝업 창 UI 작성
    AlertDialog(
        onDismissRequest = { onClosePopup() },
        title = { Text(
            text = "탑승 열차번호를 입력해 주세요",
            fontSize = 20.sp
        ) },
        text = {
            TextField(
                value = trainNum, // 열차 번호
                onValueChange = { trainNum = it },
                label = { Text("열차번호 입력") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        },

        confirmButton = {
            Button(
                onClick = {
                    trainViewModel.getSubwayData(departStation, trainNum) // 열차가 있는지 없는지 확인
                    if (trainViewModel.findTrain.value) { // 있다면 findTrain이 false에서 true바뀔 것이다.
                        // 최종적으로 여기서 알람 설정이 이루어진다고 보면 된다. 마지막 확인 버튼을 누르면 주기적으로 함수 호출 이루어 질거임
                        // 버튼을 누르면, 출발역과 열차번호를 보낸다.
                        // 먼저 출발역에서 해당 열차번호가 잡히는지 확인한다.

                        // ActDestination은 확정된 지하철역을 말하고, DesStationtmp는 Alarm 화면에서 도착지 카드를 눌렀을 때 임시로 저장하는 부분임.
                        trainViewModel.setActDesStation() // 임시 도착지였던것을
                        trainViewModel.setDepart(departStation, trainNum)

                        setRepeatingAlarm(context = context, intervalMillis = 10 * 1000, trainViewModel)
                        onClosePopup() // 팝업창 닫기
                        navController.navigate(route = Alarm.route) // 알람 진행 화면으로 이동. 이후에 다시 알람 설정창으로 돌아갈 수 없는 문제가 생김

                    }
                    else {
                        Log.d(ContentValues.TAG, "실패...")
                    }
                }
            ) {
                Text("확인")
            }
            Button(
                onClick = {
                    onClosePopup()
                }
            ) {
                Text("취소")
            }
        }
    )
}

// 검색창 필터용 함수
private fun getStation(
    searchTerm: String,
    stations: List<Station>
): List<Station> {
    return if (searchTerm != "") { // ""가 아니면 searchTerm을 포함하는 단어를 필터링 해줌.
        stations.filter { it.stationNm.contains(searchTerm, ignoreCase = true) }
    } else {
        stations
    }
}

// 알람 설정
private fun setRepeatingAlarm(context: Context, intervalMillis: Long, viewModel: TrainViewModel) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, MyAlarmReceiver::class.java)

    // intent로 필요한 데이터 보내주기. 도착역, 출발역, 열차번호
    intent.putExtra("DesStation", viewModel.ActDesStation.value) // 도착역 보내주기
    intent.putExtra("DepartStation", viewModel.DepartStation.value) // 출발역
    intent.putExtra("TrainNum", viewModel.TrainNumData.value) // 열차 번호

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0, // requestCode는 해당 알람의 식별자임
        intent,
        PendingIntent.FLAG_MUTABLE // FLAG_MUTABLE은 PendingIntent를 동적으로 조작할 수 있음. 알림 업데이트, 추가적인 액션이 가능
                                    // FLAG_IMMUTABLE은 PendingIntent를 수정할 수 없음
    )
    val startTime = SystemClock.elapsedRealtime()
    val triggerTime = startTime + intervalMillis
    alarmManager.setRepeating( // 반복 시간이 1분 밑으로는 안됨. 따라서 1분마다 호출함.
        AlarmManager.ELAPSED_REALTIME_WAKEUP,
        startTime,
        intervalMillis,
        pendingIntent
    )
}

// 알람 취소
fun cancelAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    // 알람을 실행할 동작을 정의하는 Intent 생성
    val intent = Intent(context, MyAlarmReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    // 알람 취소
    alarmManager.cancel(pendingIntent)
}


var nextStation = "" // 다음역 담을 변수
var flag = false

@SuppressLint("RestrictedApi")
class MyAlarmReceiver : ForceStopRunnable.BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)
        // 여기에 호출하고자 하는 함수를 구현한다.
        val desStation = intent?.getStringExtra("DesStation") // 도착역 ActDesStation
        val departStation = intent?.getStringExtra("DepartStation") // 출발역 DepartStation
        val trainNum = intent?.getStringExtra("TrainNum") // TrainNumData

        Log.d(ContentValues.TAG, "도착역 확인 : ${desStation}")
        Log.d(ContentValues.TAG, "출발역 확인 : ${departStation}")
        Log.d(ContentValues.TAG, "열차번호 확인 : ${trainNum}")

        // 다음역이 내가 설정한 도착역이면 알림을 울린다.
        if (nextStation == desStation) {
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            Log.d(ContentValues.TAG, "도착했음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            cancelAlarm(context) // 도착했으니까 알람을 취소함.
        }

        // flag값이 false면 아직 currentStation의 값이 바뀌지 않았음.
        if (flag == false) { // 인덱스 값이 0이면 처음하는거니까 departStation값을 그대로 넣는다.
            if (desStation != null && departStation != null && trainNum != null) {
                getSubwayData(departStation, desStation, trainNum)
            }
        }
        else { // flag == true. flag값이 true면 현재역이 바뀐거니까 바뀐 역으로 넣어준다.
            if (desStation != null && trainNum != null)
            getSubwayData(nextStation, desStation, trainNum)
        }
    }
}

// 1분마다 호출용
private fun getSubwayData(departStation: String, desStation: String, trainNum: String) {
    val trainApi: TrainApi = TrainRetrofitCreator.trainApi
    val callTrain = trainApi.getTrain(departStation)

    callTrain.enqueue(object: Callback<GetResTrain> {
        override fun onResponse(call: Call<GetResTrain>, response: Response<GetResTrain>) {
            if(response.isSuccessful) { // response check
                val responseBody: GetResTrain? = response.body()

                if (responseBody != null && responseBody.realtimeArrivalList != null) {

                    for((index, value) in responseBody.realtimeArrivalList.withIndex()) { // withIndex는 인덱스도 같이 사용하겠다는 의미
                        if (trainNum == value.btrainNo.toString()) { // 탑승하고 있는 열차 탐색
                            Log.d(ContentValues.TAG, "열차 확인됨.")
                            Log.d(ContentValues.TAG, "현재 설정된 다음역 : ${nextStation}")

                            // 출발역과 실제 열차의 다음역이 같으면 nextStation을 다음역으로 바꿔준다.
                            if (departStation == value.arvlMsg3) {
                                Log.d(ContentValues.TAG, "다음역과 열차가 도착한 역이 같아졌어요!")
                                nextStation = TrainIdNmList.SearchTrain(value.statnTid).toString()
                                flag = true
                            }

                            Log.d(ContentValues.TAG, "지하철역 : ${value.statnNm}")
                            Log.d(ContentValues.TAG, "열차 도착 예정시간 : ${value.barvlDt}")
                            Log.d(ContentValues.TAG, "열차 번호 : ${value.btrainNo}")
                            Log.d(ContentValues.TAG, "도착 메시지1 : ${value.arvlMsg2}")
                            Log.d(ContentValues.TAG, "현재 열차의 다음역  : ${value.arvlMsg3}") // 이것만 비교해도 되겠는데..?
                            Log.d(ContentValues.TAG, "도착 코드 : ${TrainSttus.SearchSttus(value.arvlCd)}")
                            Log.d(ContentValues.TAG, "열차 도착정보 생성시각  : ${value.recptnDt}")
                            Log.d(ContentValues.TAG, "")
                            Log.d(ContentValues.TAG, "")

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


// 열차 있는지 확인용. 출발역하고 열차번호 넣어서 있는지 없는지 확인할거임
private suspend fun getSubwayData(context: Context, departStation: String, trainNum: String) : Boolean {
    var findFlag = CompletableDeferred<Boolean>()

    Log.d(ContentValues.TAG, "역 이름 : ${departStation}")
    val trainApi: TrainApi = TrainRetrofitCreator.trainApi
    val callTrain = trainApi.getTrain(departStation)

    callTrain.enqueue(object: Callback<GetResTrain> {
        override fun onResponse(call: Call<GetResTrain>, response: Response<GetResTrain>) {
            if(response.isSuccessful) { // response check
                val responseBody: GetResTrain? = response.body()
                if (responseBody != null && responseBody.realtimeArrivalList != null) {

                    for( value in responseBody.realtimeArrivalList) {
                        if (trainNum == value.btrainNo.toString()) {
                            Log.d(ContentValues.TAG, "열차 찾았다!! : ${value.btrainNo}")
                            findFlag.complete(true)
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
    return try {
        findFlag.await()
    } catch (e: Exception) {
        false
    }
}









