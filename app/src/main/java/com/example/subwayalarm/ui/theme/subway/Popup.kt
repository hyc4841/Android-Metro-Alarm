package com.example.subwayalarm.ui.theme.subway

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.subwayalarm.AlarmInPrg

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Popupm( // 팝업창
    navController: NavController,
    onClosePopup: () -> Unit,
    trainviewModel: TrainViewModel,
    destinationSt: String
) {
    val destinationData = trainviewModel.DestinationList.value
    var cntStation by remember { mutableStateOf("") }
    var trainNum by remember { mutableStateOf("") }


    // 팝업 창 UI 작성
    AlertDialog(
        onDismissRequest = { onClosePopup() },
        title = { Text(
            text = "탑승역과 열차번호를 입력해주세요",
            fontSize = 20.sp
        ) },
        text = {
            Column {
                TextField(
                    value = cntStation,
                    onValueChange = { cntStation = it },
                    label = { Text("탑승역 입력") }
                )
                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = trainNum,
                    onValueChange = { trainNum = it},
                    label = { Text("탑승 열차 입력") }
                )
            }
        },

        confirmButton = {
            Button(
                onClick = {
                    trainviewModel.setDepart(cntStation, trainNum)
                    onClosePopup()
                    navController.navigate(route = AlarmInPrg.route)
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