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
import javax.inject.Inject

class EngineRepoImpl @Inject constructor(private val questionLogic: QuestionLogic) :
    EngineLogicRepository {

    private lateinit var gameSettings: GameSettings<Long>
    private val resultLogic: ResultLogic by lazy {
        ResultLogic.build(this)
    }

    override suspend fun sendGameData(): Flow<SendAble> {
        return flow {
            emit(questionLogic.gameQuestion.last())
            delay(gameSettings.roundTime)
            emit(resultLogic.resultList.last())
        }
    }

    override suspend fun recieveGameData(data: Flow<SendAble>) {
        data.collect { sendAble ->
            when (sendAble) {
                is GameSettings<*> -> {
                    try {
                        gameSettings = sendAble as GameSettings<Long>
                        commandProduceData()
                    } catch (e: ClassCastException) {
                        Log.d("ENGINE_REPO_IMPL", "BAD CAST GAMESETTINGS")
                    }
                }
                is GameResult<*> -> {
                    try {
                        resultLogic.setAndCalculate(
                            sendAble as GameResult<Long>
                        )
                    } catch (e: ClassCastException) {
                        Log.d("ENGINE_REPO_IMPL", "BAD CAST GAMERESULT")
                    }

                }
            }
        }
    }

    private suspend fun commandProduceData() {
        withContext(Dispatchers.IO) {
            while (gameSettings.roundTime > VALUE_OF_CONTINUE) {
                delay(gameSettings.roundTime)
                questionLogic.generateQuestion(gameSettings)
            }
        }
    }

    companion object {
        private const val VALUE_OF_CONTINUE = 0L
    }

}