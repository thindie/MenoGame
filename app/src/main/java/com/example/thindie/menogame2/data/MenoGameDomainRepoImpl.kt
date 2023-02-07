package com.example.thindie.menogame2.data

import com.example.thindie.menogame2.data.engine.logic.GameRoundBuilder
import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.GameRound
import com.example.thindie.menogame2.domain.entities.abstractions.Information
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenoGameDomainRepoImpl @Inject constructor(private val gameRoundBuilder: GameRoundBuilder

) : DomainRepository {
    override suspend fun getPlayScreen(): GameRound {
        TODO("Not yet implemented")
    }

    override suspend fun getInformationScreen(): Information {
        TODO("Not yet implemented")
    }

    override suspend fun addInformation(information: Information) {
        TODO("Not yet implemented")
    }


}