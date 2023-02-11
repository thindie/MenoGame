package com.example.thindie.menogame2.presentation.composables.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.menogame2.domain.entities.abstractions.Information


@Composable
fun RecordScreen(list: List<Information>, onBackArrow: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                IconButton(onClick = { onBackArrow() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            LazyColumn(){
                items(list){
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
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Divider(thickness = Dp.Hairline)
        Row(modifier = Modifier.fillMaxWidth()) {
            Column() {
                Text(playerName)
                Text(rightAnswers)
            }
            Column() {
                Text(timeInformation)
                Text(scoreInformation)
            }
        }
    }
}