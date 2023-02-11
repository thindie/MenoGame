package com.example.thindie.menogame2.data.engine.nameDataBase

import com.example.thindie.menogame2.domain.entities.PlayerInit

fun NameSaveDbModel.map(): PlayerInit {
    return PlayerInit(this.name)
}

fun PlayerInit.map(): NameSaveDbModel {
    return NameSaveDbModel(name = this.playerName)
}