package com.example.subwayalarm.ui.theme.subway

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
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
import com.example.subwayalarm.Alarm
import com.example.subwayalarm.model.Station
import com.example.subwayalarm.model.stations

@OptIn(ExperimentalMaterial3Api::class)
// @Preview(showBackground = true)
@Composable
fun SetDestination(
    navController: NavController,
    trainviewModel: TrainViewModel
) {
    val (searchTerm, updateSearchTerm) = remember { mutableStateOf("") }
    val offset = remember { mutableStateOf(0f) }
    val stname = remember { mutableStateOf("") }
    val lineNm = remember { mutableStateOf("") }

    // 팝업창
    var isPopupVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { delta ->
                    offset.value = offset.value + delta
                    delta
                }
            ),
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
            if (searchTerm != "") { // 아무것도 입력하지 않은 초기상태는 lazyColumn의 요소가 보이지 않도록 함.
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
                                    stname.value = item.stationNm
                                    lineNm.value = item.line
                                    isPopupVisible = true
                                }
                            )
                    )
                }
            }
        }
    }

    // 팝업 창이 열린 경우 PopupScreen 호출
    if (isPopupVisible) {
        Popup(
            stationName = stname.value,
            line = lineNm.value,
            onClosePopup = { isPopupVisible = false },
            navController = navController,
            trainviewModel = trainviewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Popup( // 팝업창
    navController: NavController,
    stationName: String,
    line: String,
    onClosePopup: () -> Unit,
    trainviewModel: TrainViewModel
) {
    val destinationData = trainviewModel.DestinationList.value

    // 팝업 창 UI 작성
    AlertDialog(
        onDismissRequest = { onClosePopup() },
        title = { Text(
            text = "${stationName}역을 도착역으로 설정하시겠습니까?",
            fontSize = 20.sp
        ) },
        confirmButton = {
            Button(
                onClick = {
                    trainviewModel.setDestinationCard(stationName, line)
                    onClosePopup()
                    // navController.popBackStack() 이렇게 하면 데이터 그냐 날아감. 원래화면으로 복귀할땐 다른방법 써야함. viewModels?
                    navController.navigate(route = Alarm.route)
                    Log.d(ContentValues.TAG, "dddddddddd : ${destinationData}")

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

    private fun getStation(
        searchTerm: String,
        stations: List<Station>
    ): List<Station> {
        return if (searchTerm != "") {
            stations.filter { it.stationNm.contains(searchTerm, ignoreCase = true) }
        } else {
            stations
        }
    }

