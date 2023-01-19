package com.example.thindie.menogame2.domain.entities

data class GameSettings<T>(
    val fields: T,
    val rightAnswers: T,
    val roundTime: T,
    val answerTime: T,
    val shownScores :List<T>
) : SendAble

