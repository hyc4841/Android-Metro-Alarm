package com.example.subwayalarm

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Subway
import androidx.compose.ui.graphics.vector.ImageVector

interface Destinations {
    val icon: ImageVector
    val title: Int
    val route: String
}

object Alarm : Destinations {
    override val icon = Icons.Outlined.Notifications
    override val title = R.string.bottom_nav_alarm
    override val route = "alarm"
}

object SubwayMap : Destinations {
    override val icon = Icons.Outlined.Route
    override val title = R.string.bottom_nav_train_map
    override val route = "subwaymap"
}

object AlarmInPrg : Destinations {
    override val icon = Icons.Outlined.Subway
    override val title = R.string.bottom_nav_progress
    override val route = "alarmprg"
}

object Setting : Destinations {
    override val icon = Icons.Outlined.Settings
    override val title = R.string.bottom_nav_setting
    override val route = "setting"
}

val BottomTap = listOf(Alarm, SubwayMap, AlarmInPrg, Setting)