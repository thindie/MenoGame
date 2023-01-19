package com.example.thindie.menogame2.presentation.composables.elements

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.menogame2.presentation.theme.MenoGame2Theme

@Composable
fun GameElement(color: Color) {
    Surface(
        shape = CircleShape,
        modifier = Modifier.size(72.dp),
        color = color
    ) {

    }
}

@Preview
@Composable
fun previewGameElement() {
    MenoGame2Theme {
        GameElement(Color.Green)
    }
}