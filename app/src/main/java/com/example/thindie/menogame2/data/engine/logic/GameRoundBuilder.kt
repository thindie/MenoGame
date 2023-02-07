package com.example.thindie.menogame2.data.engine.logic

import com.example.thindie.menogame2.di.DispatchersModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameRoundBuilder @Inject constructor(@DispatchersModule.IODispatcher val io: CoroutineDispatcher) {

    private val _questions = mutableListOf<GameRoundModel>()
    val questions : List<GameRoundModel>
    get() = _questions.toList()


    private fun generateQuestion(){

    }

    private fun onCancel(){

    }

    private suspend fun onQuestionTriggered(){
        withContext(io){

        }
    }

}