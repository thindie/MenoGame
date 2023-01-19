package com.example.thindie.menogame2.data.engine

import com.example.thindie.menogame2.domain.entities.SendAble
import kotlinx.coroutines.flow.Flow

interface EngineLogicRepository {
    suspend fun sendGameData(): Flow<SendAble>
    suspend fun recieveGameData(data: Flow<SendAble>)

}