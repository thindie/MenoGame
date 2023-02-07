package com.example.thindie.menogame2.domain

import com.example.thindie.menogame2.domain.entities.GameRound
import com.example.thindie.menogame2.domain.entities.abstractions.Information

interface DomainRepository {
    suspend fun getPlayScreen(): GameRound
    suspend fun getInformationScreen() : Information
    suspend fun addInformation(information: Information)
}