package com.example.thindie.menogame2.domain.entities

data class GameResult<T>
    (
    val player: MutableList<String>,
    val time: MutableList<T>,
    val score: MutableList<T>
) : SendAble