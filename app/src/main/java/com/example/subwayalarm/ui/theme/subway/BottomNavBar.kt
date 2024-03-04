package com.example.subwayalarm.ui.theme.subway

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.subwayalarm.BottomTap
import com.example.subwayalarm.Destinations
import com.example.subwayalarm.R

@Composable
fun BttomNavBar(
    navController: NavController,
    tabs: List<Destinations>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = navBackStackEntry?.destination?.route
        ?: BottomTap.find { it.route == currentDestination?.route}

    // 탭에서 강제로 navigate해서 다른 탭으로 가면 원래 탭 사라짐.

    val routes = remember { BottomTap.map { it.route } }
    if (currentRoute in routes) {
        NavigationBar(
        ) {
            tabs.forEach { tab ->
                NavigationBarItem(
                    icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                    label = { Text(stringResource(tab.title)) },
                    selected = currentRoute == tab.route, // navControllr의 상태를 변하게 만든다.

                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    alwaysShowLabel = false // 탭을 선택하지 않았을 때는 라벨을 숨겨줌
                )
            }
        }
    }
}