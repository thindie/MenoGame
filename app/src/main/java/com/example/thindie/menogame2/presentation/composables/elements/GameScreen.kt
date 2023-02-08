package com.example.thindie.menogame2.presentation.composables.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


private const val RIGHT = 0
private const val COLUMNS_ARE = 4
private const val MILLIS = 1000

@Composable
fun GameScreen(
    time: Long,
    answersNeeded: Int,
    shownList: List<Int>,
    gameOver: () -> Unit,
    roundSolved: () -> Unit,
    modifier: Modifier = Modifier
) {

    var showQuestion by remember { mutableStateOf(true) }
    var rightClicks by remember { mutableStateOf(RIGHT) }
    var solvedRound by remember { mutableStateOf(false) }



    if (rightClicks == answersNeeded - 1) {
        solvedRound = !solvedRound
        roundSolved()
        rightClicks = RIGHT
        showQuestion = !showQuestion
    }



    Column(modifier = modifier.fillMaxSize()) {
        Spacer(modifier.weight(0.8f))
        LazyVerticalGrid(
            contentPadding = PaddingValues(all = 80.dp),
            modifier = modifier.fillMaxSize(),
            columns = GridCells.Fixed(COLUMNS_ARE),
            verticalArrangement = Arrangement.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(shownList) {
                if (showQuestion)
                    RevealPad(element = UiGameElement(it, time))
                else AskPad(element = UiGameElement(it, time),
                    iterateClicks = { rightClicks++ }) {
                    gameOver()
                }
            }
        }
    }

    LaunchedEffect(solvedRound) {
        var ticker = time * 2
        do {
            if (ticker == time) {
                showQuestion = !showQuestion
            }
            delay(1000)
            ticker--
        } while (ticker > 0)
        gameOver()
    }


}

data class UiGameElement(
    val instance: Int, val time: Long
)


@Composable
fun RevealPad(
    element: UiGameElement
) {
    FloatingActionButton(
        shape = CircleShape,
        onClick = {},
        containerColor = if (element.instance != RIGHT) MaterialTheme.colorScheme.onPrimaryContainer
        else MaterialTheme.colorScheme.errorContainer
    ) {

    }
}


@Composable
fun AskPad(
    element: UiGameElement, iterateClicks: () -> Unit, gameOver: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }
    val onClick: () -> Unit = {
        if (!clicked) {
            if (element.instance == 0) iterateClicks() else gameOver()
            clicked = true
        }
    }
    FloatingActionButton(
        shape = CircleShape,
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer

    ) {

    }
}

