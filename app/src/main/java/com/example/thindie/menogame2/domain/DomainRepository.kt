package com.example.thindie.menogame2.domain

import com.example.thindie.menogame2.domain.entities.SendAble

interface DomainRepository {
    suspend fun sendData(sendAble: SendAble)
    suspend fun recieveData(): SendAble
}