package com.example.thindie.menogame2.domain.entities

data class GameSettings<T>(
    val fields: MutableList<T>
) : SendAble

