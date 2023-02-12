package com.example.thindie.menogame2.domain

import com.example.thindie.menogame2.data.engine.logic.GameRoundBuilder
import com.example.thindie.menogame2.domain.entities.GameRound
import com.example.thindie.menogame2.domain.entities.abstractions.Information
import kotlinx.coroutines.flow.Flow

interface DomainRepository {
    var gameRoundBuilder: GameRoundBuilder
    suspend fun getPlayScreen(isNewGame: Boolean, isMaster : Boolean): GameRound
    suspend fun getInformationScreen(isShowRecords: Boolean): Flow<Information>
    suspend fun addInformation(information: Information)
    suspend fun initName(): String?
}