package com.example.thindie.menogame2.data.engine.logic

import com.example.thindie.menogame2.data.engine.EngineLogicRepository
import com.example.thindie.menogame2.domain.entities.GameQuestion
import com.example.thindie.menogame2.domain.entities.GameSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuestionLogic private constructor(private val engineLogicRepository: EngineLogicRepository) {

    suspend fun generateQuestion(gameSettings: GameSettings<Long>) {
        withContext(Dispatchers.IO) {

            val fields = mutableListOf<Long>()
            for (i in 0 until gameSettings.fields - gameSettings.rightAnswers) {
                fields.add(0L)
            }
            for (i in 0 until gameSettings.rightAnswers) {
                fields.add(1L)
            }
            val question = GameQuestion(
                fields = fields,
                roundTime = gameSettings.roundTime,
                rightAnswers = gameSettings.rightAnswers
            )
            engineLogicRepository.sendGameData(question)
        }

    }


    companion object {
        fun build(
            engineLogicRepository: EngineLogicRepository,
        ): QuestionLogic {
            return QuestionLogic(engineLogicRepository)
        }
    }
}