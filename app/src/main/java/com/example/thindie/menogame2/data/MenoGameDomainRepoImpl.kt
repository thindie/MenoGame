package com.example.thindie.menogame2.data

import com.example.thindie.menogame2.data.engine.EngineLogicRepository
import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.SendAble
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenoGameDomainRepoImpl @Inject constructor(
    private val engineLogicRepository: EngineLogicRepository,
) : DomainRepository {


    override suspend fun recieveData(sendAble: Flow<SendAble>) {
        engineLogicRepository.recieveGameData(sendAble)
    }

    override suspend fun sendData(): Flow<SendAble> {
       return engineLogicRepository.sendGameData()
    }
}