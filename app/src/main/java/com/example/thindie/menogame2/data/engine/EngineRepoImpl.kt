package com.example.thindie.menogame2.data.engine

import android.util.Log
import com.example.thindie.menogame2.data.engine.logic.QuestionLogic
import com.example.thindie.menogame2.data.engine.logic.ResultLogic
import com.example.thindie.menogame2.domain.entities.GameResult
import com.example.thindie.menogame2.domain.entities.GameSettings
import com.example.thindie.menogame2.domain.entities.SendAble
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EngineRepoImpl @Inject constructor() : EngineLogicRepository {
    private lateinit var gameSettings: GameSettings<Long>

    private val questionLogic: QuestionLogic by lazy {
        QuestionLogic.build(this)
    }
    private val resultLogic: ResultLogic by lazy {
        ResultLogic.build(this)
    }

    override suspend fun sendGameData(sendAble: SendAble): Flow<SendAble> {
        return flow {
            emit(sendAble)
        }
    }

    override suspend fun recieveGameData(data: Flow<SendAble>) {
        data.collect { sendAble ->
            when (sendAble) {
                is GameSettings<*> -> {
                    try {
                        gameSettings = sendAble as GameSettings<Long>
                    } catch (e: ClassCastException) {
                        Log.d("ENGINE_REPO_IMPL", "BAD CAST GAMESETTINGS")
                    }
                }
                is GameResult<*> -> {

                    resultLogic.calculateAndGet(
                        System.currentTimeMillis(),
                        sendAble as GameResult<Long>
                    )
                }
            }
        }
    }

    override suspend fun commandProduceData() {
        withContext(Dispatchers.IO) {
            while (true) {
                delay(gameSettings.roundTime)
                questionLogic.generateQuestion(gameSettings)
            }
        }
    }


}