package com.example.subwayalarm

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.subwayalarm.ui.theme.subway.AlarmScreen
import com.example.subwayalarm.ui.theme.subway.ProgressScreen
import com.example.subwayalarm.ui.theme.subway.SetDepart
import com.example.subwayalarm.ui.theme.subway.SetDestination
import com.example.subwayalarm.ui.theme.subway.SettingScreen
import com.example.subwayalarm.ui.theme.subway.SubwayMapScreen
import com.example.subwayalarm.ui.theme.subway.TrainViewModel


@Composable
fun SubwayNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    trainViewModel: TrainViewModel,
    context: Context
) {
    NavHost(
        navController = navController,
        startDestination = Alarm.route,
        modifier = modifier
    ) {
        composable(route = Alarm.route) {
            AlarmScreen(navController, trainViewModel)
        }

        composable(route = SubwayMap.route) {
            SubwayMapScreen()
        }

        composable(route = AlarmInPrg.route) {
            ProgressScreen(navController, trainViewModel)
        }

        composable(route = Setting.route) {
            SettingScreen()
        }


        composable("setDestination") {
            SetDestination(navController, trainViewModel) // 화면 안에 화면에서 이동하고 싶으면 그냥 추가하고 navController 보내주면 된다...
        }
        composable("setDepart") {
            SetDepart(navController, trainViewModel, context)
        }


    }
}