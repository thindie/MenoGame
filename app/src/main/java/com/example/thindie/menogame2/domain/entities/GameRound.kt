package com.example.thindie.menogame2.domain.entities

data class GameRound(
    val shownScore: String,
    val answerTime: Long,
    val showPadTime: Long,
    val questionPad: List<Int>,
    val shownPad: List<Int>
)