package com.example.subwayalarm

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.subwayalarm.ui.theme.SubwayAlarmTheme
import com.example.subwayalarm.ui.theme.subway.BttomNavBar
import com.example.subwayalarm.ui.theme.subway.TrainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val context = this
        super.onCreate(savedInstanceState)
        setContent {
            SubwayAlarmApp(context)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubwayAlarmApp(
    context: Context
) {
    SubwayAlarmTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen = BottomTap.find { it.route == currentDestination?.route } ?: Alarm

        val trainViewModel: TrainViewModel = viewModel()

        Scaffold(
            bottomBar = {
                BttomNavBar(
                    navController = navController,
                    tabs = BottomTap
                )
            }

        ) { innerPadding ->
            SubwayNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                trainViewModel = trainViewModel,
                context = context

            )

        }
    }
}