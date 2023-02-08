package com.example.thindie.menogame2.presentation.composables.elements

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thindie.menogame2.presentation.MainViewModel
import com.example.thindie.menogame2.presentation.MainViewModel.ViewState.*


@Composable
fun State() {
    val viewModel: MainViewModel = viewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    when (viewState) {
        is onGame -> {
            val screen = (viewState as onGame).gameScreen
            GameScreen(time = screen.answerTime,
                shownList = screen.questionPad,
                answersNeeded = screen.howManyRightAnswers,
                gameOver = { viewModel.onEndGame() },
                roundSolved = { viewModel.onSolved() })
        }
        is onLoading -> {
            val timing = (viewState as onLoading).timing
            LoadingScreen(timing = timing) { }
        }
        is onError -> {}
        is onFinish -> { viewModel.onEndGame() }
    }
}

