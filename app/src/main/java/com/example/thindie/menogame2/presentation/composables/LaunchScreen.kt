package com.example.thindie.menogame2.presentation.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.menogame2.presentation.composables.elements.GameElement
import com.example.thindie.menogame2.presentation.theme.MenoGame2Theme
import kotlinx.coroutines.delay

@Composable

fun LoadingScreen() {
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
    val floatValue = 0.5f
    val endFloatValue = 1.6f

    LaunchedEffect(key1 = key.value) {

        while (true) {
            key.value = !key.value
            delay(200)
            colorList.shuffle()


        }

    }



    Surface(modifier = modifier) {

        Column(
            modifier = modifier.padding(all = 5.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.12f))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(all = 20.dp),
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(0.5f)
                    .scale(animateFloatAsState(targetValue = if (key.value) floatValue else endFloatValue).value),
                userScrollEnabled = false
            ) {
                items(colorList)
                { item ->
                    GameElement(item)
                }

            }
            Spacer(modifier = Modifier.weight(0.1f))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewLoadingScreen() {
    MenoGame2Theme() {
        LoadingScreen()

    }
}
