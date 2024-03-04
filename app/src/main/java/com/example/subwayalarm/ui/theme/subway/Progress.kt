package com.example.subwayalarm.ui.theme.subway

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ProgressScreen(
    navController: NavController, // 화면 이동 관련
    trainviewModel: TrainViewModel
) {

    val cntStationData = trainviewModel.DepartStation.value
    val destinationData = trainviewModel.DesStationtmp.value
    val trainNumData = trainviewModel.TrainNumData.value

    Column {
        Text(text = "탑승역 : ${cntStationData}")
        Text(text = "탑승 열차번호 : ${trainNumData}")
        Text(text = "도착역 : ${destinationData}")

        // trainviewModel.getSubwayData(cntStationData)

    }
}