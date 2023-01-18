package com.example.thindie.menogame2.domain

import com.example.thindie.menogame2.domain.entities.SendAble
import kotlinx.coroutines.flow.Flow

interface DomainRepository {
    suspend fun recieveData(sendAble: Flow<SendAble>)
    suspend fun sendData(): Flow<SendAble>
}