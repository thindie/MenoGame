package com.example.thindie.menogame2.domain.entities

data class GameQuestion<T>(
    val fields: MutableList<T>,
    val rightAnswers : T,
    val roundTime: T,
) : SendAble