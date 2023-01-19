package com.example.thindie.menogame2.data.engine.logic

import android.util.Log
import com.example.thindie.menogame2.data.engine.EngineLogicRepository
import com.example.thindie.menogame2.domain.entities.GameResult
import com.example.thindie.menogame2.domain.entities.GameSettings
import com.example.thindie.menogame2.domain.entities.SendAble
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

object EngineRepoImpl :
    EngineLogicRepository {
    private val questionLogic: QuestionLogic by lazy {
        QuestionLogic.build()
    }
    private val resultLogic: ResultLogic by lazy {
        ResultLogic.build(this)
    }

    private lateinit var gameSettings: GameSettings<Long>


    override suspend fun sendGameData(): Flow<SendAble> {
        return flow {
            Log.d("SERVICE_TAG", "sendData + ${questionLogic.gameQuestion.toString()} ")
            Log.d("SERVICE_TAG", "${questionLogic.hashCode()}  question logic in function")
            emit(questionLogic.gameQuestion.last())
            delay((gameSettings.roundTime * 1000) / 2)
            emit(resultLogic.resultList.last())

        }
    }

    override suspend fun recieveGameData(data: Flow<SendAble>) {
        data.collect { sendAble ->
            when (sendAble) {
                is GameSettings<*> -> {
                    try {
                        @Suppress("UNCHECKED_CAST")
                        gameSettings = sendAble as GameSettings<Long>
                        Log.d("SERVICE_TAG", "CatchDataGAMESETTINGS")
                        commandProduceData()
                    } catch (e: ClassCastException) {
                        Log.d("ENGINE_REPO_IMPL", "BAD CAST GAMESETTINGS")
                    }
                }
                is GameResult<*> -> {
                    try {
                        @Suppress("UNCHECKED_CAST")
                        Log.d("SERVICE_TAG", "CatchDataGAMERESULT")
                        resultLogic.setAndCalculate(
                            sendAble as GameResult<Long>
                        )
                    } catch (e: ClassCastException) {
                        Log.d("ENGINE_REPO_IMPL", "BAD CAST GAMERESULT")
                    }
                }
                else -> {
                    Log.d("SERVICE_TAG", "${questionLogic.hashCode()}  question in ELSE")
                    gameSettings = QuestionLogic.giveInitialGameSettingsLong()
                    commandProduceData()
                }
            }
        }
    }

    private suspend fun commandProduceData() {
        withContext(Dispatchers.Default) {
            while (gameSettings.roundTime > VALUE_OF_CONTINUE) {

                questionLogic.generateQuestion(gameSettings)
                delay((gameSettings.roundTime * 1000) / 2)
            }
        }
    }

    private const val VALUE_OF_CONTINUE = 0L


}