package com.example.thindie.menogame2.data.engine.logic

import com.example.thindie.menogame2.domain.entities.GameRound

data class GameRoundModel(
    val shownScore: String,
    val answerTime: Long,
    val showPadTime: Long,
    val questionPad: List<Int>,
    val shownPad: List<Int>
)

fun GameRoundModel.transform() : GameRound {
  return   GameRound(this.shownScore, this.answerTime, this.showPadTime, this.questionPad, this.shownPad)
}