package com.example.subwayalarm.ui.theme.subway

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.subwayalarm.R

@Preview(showBackground = true)
@Composable
fun SubwayMapScreen(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.trainma),
            contentDescription = null,
            modifier = Modifier

        )

    }
}