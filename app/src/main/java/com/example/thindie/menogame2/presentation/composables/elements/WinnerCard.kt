package com.example.thindie.menogame2.presentation.composables.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.menogame2.presentation.theme.MenoGame2Theme

@Composable
fun WinnerCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {
        Surface(
            tonalElevation = 80.dp, modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
            shape = ShapeDefaults.ExtraLarge,
            shadowElevation = 110.dp
        ) {
            Column(Modifier.fillMaxSize().padding(start = 20.dp)) {
                Text(text = "1", style =  MaterialTheme.typography.headlineLarge)
                Divider(Modifier.padding(all = 10.dp))
                Text(text = "text", style =  MaterialTheme.typography.bodyMedium)
            }
        }
    }

}

@Preview
@Composable
fun previewWinnerCard() {
    MenoGame2Theme {
        WinnerCard()
    }
}