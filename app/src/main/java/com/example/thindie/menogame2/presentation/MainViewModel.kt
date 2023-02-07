package com.example.thindie.menogame2.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thindie.menogame2.domain.useCase.GetUserInformationUseCase
import com.example.thindie.menogame2.domain.useCase.SendGameInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sendUserData: SendGameInformationUseCase,
    private val getUserData: GetUserInformationUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.onLoading)
    val viewState: StateFlow<ViewState>
        get() = _viewState


    init {
        onStart()
    }


    private fun onLoadScreen() {
        viewModelScope.launch {


        }
    }

        fun onStart() {

        }


        fun onDataWork() {


        }

        fun onEndGame() {

        }

        sealed class ViewState {
            object onSuccess: ViewState()
            object onLoading: ViewState()

            object onError: ViewState()
        }



}