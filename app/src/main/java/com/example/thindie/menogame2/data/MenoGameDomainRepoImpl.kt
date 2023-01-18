package com.example.thindie.menogame2.data

import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.SendAble
import javax.inject.Inject

class MenoGameDomainRepoImpl @Inject constructor() : DomainRepository {
    override suspend fun sendData(sendAble: SendAble) {
        TODO("Not yet implemented")
    }

    override suspend fun recieveData(): SendAble {
        TODO("Not yet implemented")
    }
}