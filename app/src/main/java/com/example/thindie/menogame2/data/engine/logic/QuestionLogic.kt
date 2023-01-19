package com.example.thindie.menogame2.data.engine.logic

import android.util.Log
import com.example.thindie.menogame2.data.engine.EngineLogicRepository
import com.example.thindie.menogame2.domain.entities.GameQuestion
import com.example.thindie.menogame2.domain.entities.GameSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class QuestionLogic() {

    private val _list: MutableList<GameQuestion<Long>> = mutableListOf()
    val gameQuestion: List<GameQuestion<Long>>
        get() = _list

    suspend fun generateQuestion(gameSettings: GameSettings<Long>) {
        withContext(Dispatchers.IO) {
            Log.d("SERVICE_TAG", "generateData")
            Log.d("SERVICE_TAG", "${this@QuestionLogic.hashCode()} Question Logic inside class")
            val fields = mutableListOf<Long>()
            for (i in 0 until gameSettings.fields - gameSettings.rightAnswers) {
                fields.add(0L)
            }
            for (i in 0 until gameSettings.rightAnswers) {
                fields.add(1L)
            }
            fields.shuffle()
            val question = GameQuestion(
                fields = fields,
                roundTime = gameSettings.roundTime,
                rightAnswers = gameSettings.rightAnswers,
                answerTime = gameSettings.answerTime,
                shownScores = gameSettings.shownScores
            )
            _list.add(question)
            Log.d("SERVICE_TAG", _list.toString())
        }

    }

    companion object {
        fun build() : QuestionLogic {
            return QuestionLogic()
        }

        fun giveInitialGameSettingsLong(): GameSettings<Long> {
            return GameSettings(
                fields = 9L,
                rightAnswers = 2L,
                roundTime = 6L,
                answerTime = 3L,
                shownScores = listOf<Long>().shuffled()
            )
        }
    }
}