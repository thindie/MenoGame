package com.example.thindie.menogame2.data.engine.logic

import com.example.thindie.menogame2.domain.entities.GameRound

data class GameRoundModel(
    val shownScore: Int,
    val answerTime: Long,
    val showPadTime: Long = answerTime,
    val questionPad: List<Int>,
    val howManyAnswers: Int,
)

fun GameRoundModel.transform(): GameRound {
    return GameRound(
        this.shownScore.toString(),
        this.answerTime,
        this.showPadTime,
        this.questionPad,
        this.howManyAnswers,
    )
}

