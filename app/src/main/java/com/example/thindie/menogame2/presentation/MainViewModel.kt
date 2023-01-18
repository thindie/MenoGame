package com.example.thindie.menogame2.presentation

import androidx.lifecycle.ViewModel
import com.example.thindie.menogame2.domain.useCase.UserRecieveDataUseCase
import com.example.thindie.menogame2.domain.useCase.UserSendDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    sendUserData: UserSendDataUseCase,
    recieveUserData: UserRecieveDataUseCase
) : ViewModel() {

    fun onStartGame() {

    }

    fun onUserActInGame() {

    }

    fun onDataWork() {

    }

    fun onEndGame() {

    }
}