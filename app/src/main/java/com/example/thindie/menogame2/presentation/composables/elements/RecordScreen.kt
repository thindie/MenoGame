package com.example.thindie.menogame2.presentation.composables.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.menogame2.domain.entities.abstractions.Information


@Composable
fun RecordScreen(list: List<Information>, message: String, onBackArrow: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                IconButton(onClick = { onBackArrow() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Text(
                text = message,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 20.dp, bottom = 8.dp)
            )

            LazyColumn() {
                items(list) {
                    RecordScreenElement(
                        playerName = it.playerName ?: "",
                        scoreInformation = it.scoreInformation ?: "",
                        timeInformation = it.timeInformation ?: "",
                        rightAnswers = it.questionsQuota ?: ""
                    )
                }
            }
        }

    }
}

@Composable
fun RecordScreenElement(
    playerName: String, scoreInformation: String, timeInformation: String, rightAnswers: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(all = 8.dp)
    ) {
        Divider(thickness = Dp.Hairline)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurface
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f)
            ) {
                Text(NAME.plus(playerName))
                Text(ANSWERS.plus(rightAnswers))
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f)
            ) {
                Text(TIME.plus(timeInformation).plus(SEC))
                Text(SCORE.plus(scoreInformation))
            }
        }
    }
}

private const val NAME = " by "
private const val ANSWERS = "answers: "
private const val TIME = "in timing: "
private const val SCORE = "score: "
private const val SEC = " sec."