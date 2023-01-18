package com.example.thindie.menogame2.data

import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.SendAble
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenoGameDomainRepoImpl @Inject constructor() : DomainRepository {
    override suspend fun sendData(sendAble: Flow<SendAble>) {
        TODO("Not yet implemented")
    }

    override suspend fun recieveData(): Flow<SendAble> {
        TODO("Not yet implemented")
    }
}