package com.example.subwayalarm.ui.theme.subway

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AlarmScreen(
    navController: NavController, // 화면 이동 관련
    trainviewModel: TrainViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 도착역 리스트 가져오기
        val destinationData = trainviewModel.DestinationList.value

        // 선택한 도착역들이 카드 모양으로 뜨는 곳
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                items = destinationData // items 함수에 key를 적어주지 않아도 알아서 내부적으로 고유 키값을 생성해줌.
            ) { item ->
                DestinationCard( // 도착역을 선택을 하면
                    drawable = item.lineImage,
                    text = item.stationNm,
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable(
                            onClick = {
                                // 카드를 누르면 viewModel에 도착역 변수를 설정해 줌.
                                trainviewModel.setDestination(item.stationNm)
                                // 그리고 출발역 설정 화면으로 넘어감.
                                navController.navigate("setDepart")
                            }
                        )
                        .background(
                            color = trainviewModel.isActiveCard(item.stationNm) // 도착역으로 설정하면 활성화 표시 되도록 함. setDepart에서 알람 활성화 하면
                            // ActDesStation의 값이 DesStationtmp값으로 변하고 실제로 확정 되었다는 것을 표시. 따라서 활성화된 도착지의 배경을 빨갛게 만든다는 개념
                        )
                )
            }
        }

        // 도착역 추가 버튼 부분
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                content = {
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = null,
                        Modifier
                            .size(70.dp),
                        tint = Color.Gray
                    )
                },
                // 버튼 누르면 다시 역 검색 화면으로 넘어간다. 이번엔 출발역 검색 화면
                onClick = { navController.navigate("setDestination") },
                modifier = Modifier
                    .size(100.dp),
            )
            Text(
                text = "도착역을 추가해 주세요",
                color = Color.Gray,
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TextLayout(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                content = {
                    Icon(
                        imageVector = Icons.Outlined.AddCircle,
                        contentDescription = null,
                        Modifier
                            .size(70.dp),
                        tint = Color.Gray
                    )
                },
                onClick = {  },
                modifier = Modifier
                    .size(100.dp),
            )
            Text(
                text = "도착역을 추가해 주세요",
                color = Color.Gray,
            )
        }
    }
}



