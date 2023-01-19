package com.example.thindie.menogame2.presentation.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.menogame2.presentation.composables.elements.GameElement
import com.example.thindie.menogame2.presentation.theme.MenoGame2Theme
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(onClickStart: () -> Unit, onClickBest: () -> Unit) {
    val modifier = Modifier.fillMaxSize()
    val colorList = mutableListOf<Color>(
        MaterialTheme.colorScheme.onSurfaceVariant,
        MaterialTheme.colorScheme.surfaceTint,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.onTertiaryContainer,
    )
    val key = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = key.value) {
        delay(500)
        colorList.shuffle()
        key.value = !key.value
    }
    Surface(modifier = modifier) {
        Column(
            modifier = modifier.padding(all = 5.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.02f))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(all = 20.dp),
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(0.5f),
                userScrollEnabled = false
            ) {
                items(colorList)
                { item ->
                    GameElement(item)
                }

            }
            Spacer(modifier = Modifier.weight(0.1f))
            TextButton(
                onClick = { onClickBest() },
                Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.1f),
                border = BorderStroke(Dp.Hairline, MaterialTheme.colorScheme.onSurfaceVariant)
            ) {
                Text(text = "Best")
            }
            Spacer(modifier = Modifier.height(5.dp))
            TextButton(
                onClick = { onClickStart() },
                Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.1f),
                shape = ShapeDefaults.ExtraLarge,
                border = BorderStroke(Dp.Hairline, MaterialTheme.colorScheme.onSurfaceVariant)
            ) {
                Text(text = "New Game")
            }
            Spacer(modifier = Modifier.weight(0.05f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewHomeScreen() {
    MenoGame2Theme() {
        HomeScreen(onClickStart = { /*TODO*/ }) {

        }
    }
}