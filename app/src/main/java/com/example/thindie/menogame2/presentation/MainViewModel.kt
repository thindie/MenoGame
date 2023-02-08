package com.example.thindie.menogame2.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.menogame2.domain.entities.GameRound
import com.example.thindie.menogame2.domain.useCase.GetPlayScreenUseCase
import com.example.thindie.menogame2.domain.useCase.GetUserInformationUseCase
import com.example.thindie.menogame2.domain.useCase.SendGameInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sendUserData: SendGameInformationUseCase,
    private val getUserData: GetUserInformationUseCase,
    private val getPlayScreenUseCase: GetPlayScreenUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.onLoading(INITIAL_LOADING))
    val viewState: StateFlow<ViewState>
        get() = _viewState.asStateFlow()


      fun onLoadScreen(timing: Long) {
        viewModelScope.launch {
            _viewState.value = ViewState.onLoading(timing)
        }
    }

    fun onStart() {
        viewModelScope.launch {
           _viewState.value = ViewState.onGame(getPlayScreenUseCase())
        }
    }

    fun onSolved(){

        viewModelScope.launch {
            _viewState.value = ViewState.onGame(getPlayScreenUseCase())
        }

    }


    fun onDataWork() {


    }

    fun onEndGame() {
        Log.d("SERVICE_TAG", "GAME OVER")
    }

    sealed class ViewState {
        data class onGame(val gameScreen: GameRound) : ViewState()
        data class onLoading(val timing: Long) : ViewState()

        object onFinish : ViewState()
        object onError : ViewState()
    }
companion object{
    private const val INITIAL_LOADING = 2000L
    private const val SOLVED_LOADING = 20L
}

}