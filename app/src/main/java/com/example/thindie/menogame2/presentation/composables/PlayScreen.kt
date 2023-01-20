package com.example.thindie.menogame2.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.menogame2.presentation.composables.elements.GameElement
import com.example.thindie.menogame2.presentation.theme.MenoGame2Theme

@Composable
fun PlayScreen(list: List<Long>) {
    val colorList = mutableListOf<Color>(
        MaterialTheme.colorScheme.onSurfaceVariant,
        MaterialTheme.colorScheme.surfaceTint,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.onTertiaryContainer,
    )
    colorList.shuffle()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.weight(0.5f))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                userScrollEnabled = false,
                contentPadding = PaddingValues(all = 20.dp),
                modifier = Modifier.weight(0.5f)
            ) {
                items(list) { item ->
                    GameElement(color = colorList[0])
                }
            }
        }

    }

}


@Composable
fun QuizzScreen(list: List<Long>) {
    val colorList = mutableListOf<Color>(
        MaterialTheme.colorScheme.onSurfaceVariant,
        MaterialTheme.colorScheme.surfaceTint,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.onTertiaryContainer,
    )
    colorList.shuffle()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.weight(0.5f))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                userScrollEnabled = false,
                contentPadding = PaddingValues(all = 20.dp),
                modifier = Modifier.weight(0.5f)
            ) {
                items(list) { item ->
                    GameElement(color = if (item == 1L) colorList[0] else colorList[1])
                }
            }
        }

    }
}

@Preview
@Composable
fun previewPlayScreen(){
    MenoGame2Theme {
        PlayScreen(list = listOf(1,1,1,1,1,1,0))
    }
}

@Preview
@Composable
fun previewQuizz(){
    MenoGame2Theme {
        QuizzScreen(list = listOf(1,1,1,1,1,0,0))
    }
}