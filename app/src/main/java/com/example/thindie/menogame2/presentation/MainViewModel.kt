package com.example.thindie.menogame2.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.menogame2.domain.entities.GameRound
import com.example.thindie.menogame2.domain.entities.PlayerInit
import com.example.thindie.menogame2.domain.entities.abstractions.Information
import com.example.thindie.menogame2.domain.useCase.GetPlayScreenUseCase
import com.example.thindie.menogame2.domain.useCase.GetUserInformationUseCase
import com.example.thindie.menogame2.domain.useCase.InitNameUseCase
import com.example.thindie.menogame2.domain.useCase.SendGameInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sendUserData: SendGameInformationUseCase,
    private val getUserData: GetUserInformationUseCase,
    private val getPlayScreenUseCase: GetPlayScreenUseCase,
    private val initNameUseCase: InitNameUseCase
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
            _viewState.value = ViewState.onStart(initNameUseCase())
        }

    }


    fun onSolved() {
        viewModelScope.launch {
            _viewState.value = ViewState.onGame(getPlayScreenUseCase())
        }
    }


    fun onSavePlayer(name: String) {
        viewModelScope.launch {
            sendUserData(PlayerInit(name))
        }
    }

    fun onShowRecord() {
        viewModelScope.launch {
            getUserData().collect {
                val informationList = mutableListOf<Information>()
                informationList.add(it)
                _viewState.value = ViewState.onRecord(informationList)
            }
        }
    }


    fun onEndGame() {
        Log.d("SERVICE_TAG", "GAME OVER")
    }

    sealed class ViewState {
        data class onGame(val gameScreen: GameRound) : ViewState()
        data class onLoading(val timing: Long) : ViewState()
        data class onRecord(val information: List<Information>) : ViewState()
        data class onStart(val playerName: String?) : ViewState()
        object onFinish : ViewState()
        object onError : ViewState()
    }

    companion object {
        private const val INITIAL_LOADING = 2000L
    }

}