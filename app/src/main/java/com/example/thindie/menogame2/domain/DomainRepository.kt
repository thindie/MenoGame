package com.example.thindie.menogame2.domain

import com.example.thindie.menogame2.domain.entities.GameRound
import com.example.thindie.menogame2.domain.entities.abstractions.Information
import kotlinx.coroutines.flow.Flow

interface DomainRepository {
    suspend fun getPlayScreen(): GameRound
    suspend fun getInformationScreen(): Flow<Information>
    suspend fun addInformation(information: Information)

    suspend fun initName(): String?
}