package com.example.thindie.menogame2.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.menogame2.domain.entities.GameQuestion
import com.example.thindie.menogame2.domain.entities.GameResult
import com.example.thindie.menogame2.domain.entities.GameSettings
import com.example.thindie.menogame2.domain.entities.SendAble
import com.example.thindie.menogame2.domain.useCase.UserRecieveDataUseCase
import com.example.thindie.menogame2.domain.useCase.UserSendDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sendUserData: UserSendDataUseCase,
    private val recieveUserData: UserRecieveDataUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.StartScreen(Unit))
    val viewState: StateFlow<ViewState>
        get() = _viewState

    fun onStartGame() {
        viewModelScope.launch {
            sendUserData(
                flow {
                    //send SendAble to DataLayer - says "Im here"
                    emit(
                        object : SendAble {}
                    )
                }
            )
        }
        viewModelScope.launch {
            Log.d("SERVICE_TAG", "ondataWork")
            delay(5000)
            onDataWork()
            delay(1000)
            onUserActInGame(
                GameResult<Long>(
                    mutableListOf(),
                    mutableListOf(1003),
                    mutableListOf()
                )
            )
        }
    }

    fun onUserActInGame(gameResult: GameResult<Long>) {
        viewModelScope.launch {
            //endParty
            if (gameResult.time.last() == END_GAME) {
                sendUserData(
                    flow {
                        emit(
                            END_GAME_GAMESETTINGS
                        )
                        delay(SOMETIME)
                        onEndGame()
                    }

                )
            }
            //play
            else {
                sendUserData(
                    flow {
                        emit(
                            gameResult
                        )
                    }
                )
            }
        }
    }

    fun onDataWork() {
        viewModelScope.launch {
            recieveUserData.invoke().collect { fromData ->
                when (fromData) {
                    is GameQuestion<*> -> _viewState.value = ViewState.PlayScreen(fromData)
                    is GameSettings<*> -> { /*recieve new delays */}
                    is GameResult<*> -> _viewState.value = ViewState.EndScreen(fromData)
                }
            }
        }

    }

    fun onEndGame() {
        viewModelScope.launch {
            sendUserData.invoke(
                flow {
                    emit(
                        END_GAME_GAMESETTINGS
                    )
                }
            )
        }
    }

    sealed class ViewState {
        data class Loading(val unit: Unit) : ViewState()
        data class PlayScreen<T>(val question: GameQuestion<T>) : ViewState()
        data class StartScreen(val unit: Unit) : ViewState()
        data class EndScreen<T>(val gameResult: GameResult<T>) : ViewState()
    }

    companion object {
        private const val END_GAME = 0L
        private const val SOMETIME = 300L
        private val END_GAME_GAMESETTINGS = GameSettings(
            fields = 1,
            rightAnswers = 1,
            roundTime = 0L,
            answerTime = 1,
            listOf()
        )
    }
}