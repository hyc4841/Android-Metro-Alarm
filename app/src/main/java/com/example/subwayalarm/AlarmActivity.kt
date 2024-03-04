package com.example.subwayalarm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DummyScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DummyScreen(
) {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
         Text(text = "수고했다...")
    }
}