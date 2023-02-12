package com.example.thindie.menogame2.presentation.composables.elements

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.menogame2.presentation.MainViewModel
import com.example.thindie.menogame2.presentation.MainViewModel.ViewState.*


@Composable
fun State(onExit: () -> Unit) {
    val viewModel: MainViewModel = viewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    when (viewState) {
        is onGame -> {
            val screen = (viewState as onGame).gameScreen
            GameScreen(
                time = screen.answerTime,
                shownList = screen.questionPad,
                answersNeeded = screen.howManyRightAnswers,
                gameOver = { viewModel.onEndGame() },
                roundSolved = { viewModel.onSolved() },
                onClickBack = { viewModel.onStartScreen() },
                score = screen.shownScore
            )
        }
        is onLoading -> {
            val timing = (viewState as onLoading).timing
            LoadingScreen(timing = timing) { viewModel.onStartScreen() }
        }
        is onError -> {}
        is onRecord -> {
            val information = (viewState as onRecord).information
            RecordScreen(information, message = RECORDS) { viewModel.onStartScreen() }
        }
        is onFinish -> {
            val information = (viewState as onFinish).information
            RecordScreen(information, message = RESULT) { viewModel.onStartScreen() }
        }
        is onStart -> {
            StartScreen(
                name = (viewState as onStart).playerName,
                onNewGame = { viewModel.onStartGame(false) },
                onRecord = { viewModel.onShowRecord() },
                onSavePlayer = { viewModel.onSavePlayer(it) },
                onMaster = {viewModel.onStartGame(true)},
                onExit = { Log.d("SERVICE_TAG", "ONEXIT"); onExit() }
            )
        }
    }
}
private const val RECORDS = "  Players with awesome scores "
private const val RESULT = "Your result is:  "
