package com.example.thindie.menogame2.presentation.composables.elements

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private const val TO_WHOLE_TIME = 2
private const val RIGHT = 0
private const val COLUMNS_ARE = 4
private const val MILLIS = 1000L

@SuppressLint("SuspiciousIndentation")
@Composable
fun GameScreen(
    time: Long,
    score: String,
    answersNeeded: Int,
    shownList: List<Int>,
    gameOver: () -> Unit,
    onClickBack: () -> Unit,
    roundSolved: () -> Unit,
    modifier: Modifier = Modifier
) {

    var showQuestion by remember { mutableStateOf(true) }
    var rightClicks by remember { mutableStateOf(RIGHT) }
    var solvedRound by remember { mutableStateOf(false) }



    if (rightClicks == answersNeeded) {
        solvedRound = !solvedRound
        roundSolved()
        rightClicks = RIGHT
        showQuestion = !showQuestion
    }


    Surface(
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp)
            ) {
                IconButton(onClick = { onClickBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = modifier.weight(0.7f))
                Text(
                    score,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Divider(modifier.fillMaxWidth(), thickness = Dp.Hairline)
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
                        iterateClicks = { rightClicks++ }, gameOver = {gameOver()})
                }
            }
        }
    }

    LaunchedEffect(solvedRound) {
         val delayed = time.times(MILLIS);
            delay(delayed)
        showQuestion = !showQuestion
             delay(delayed.times(2.toLong()))

       if(!solvedRound) {gameOver()}
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
        containerColor = if (element.instance != RIGHT) MaterialTheme.colorScheme.onPrimaryContainer.copy(
            alpha = 0.9f
        )
        else MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.9f)
    ) {

    }
}


@Composable
fun AskPad(
    element: UiGameElement,
    iterateClicks: () -> Unit,
    gameOver: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }

    val onClick: () -> Unit = {
        if (!clicked) {
            if (element.instance == RIGHT) {
                Log.d("SERVICE_TAG", "RIGHT_CLICK")
                iterateClicks();
            } else {
                gameOver()
                Log.d("SERVICE_TAG", "MISSCLICK")
            }
        } else {
            Log.d("SERVICE_TAG", "MISSCLICK")
             gameOver()
        }
        clicked = true
    }
    FloatingActionButton(
        shape = CircleShape,
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer

    ) {
        Icon(
            imageVector = Icons.Default.Quiz,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.surface.copy(0.5f)
        )
    }
}

