package com.example.thindie.menogame2.data.engine.logic

import com.example.thindie.menogame2.domain.entities.GameQuestion
import com.example.thindie.menogame2.domain.entities.GameSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionLogic @Inject constructor() {

    private val _list: MutableList<GameQuestion<Long>> = mutableListOf()
    val gameQuestion: List<GameQuestion<Long>>
        get() = _list

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
                rightAnswers = gameSettings.rightAnswers,
                answerTime = gameSettings.answerTime
            )
            _list.add(question)
        }

    }
}