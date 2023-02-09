package com.example.thindie.menogame2.data

import com.example.thindie.menogame2.data.engine.dataBase.MenoRecordsDao
import com.example.thindie.menogame2.data.engine.logic.GameRoundBuilder
import com.example.thindie.menogame2.data.engine.logic.transform
import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.GameRound
import com.example.thindie.menogame2.domain.entities.PlayerInit
import com.example.thindie.menogame2.domain.entities.abstractions.Information
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val UNNAMED = "unnamed"
class MenoGameDomainRepoImpl @Inject constructor(
    private val gameRoundBuilder: GameRoundBuilder,
    private val menoRecordsDao: MenoRecordsDao
) : DomainRepository {

    private lateinit var playerInit: PlayerInit
    override suspend fun getPlayScreen(): GameRound =
        gameRoundBuilder.generateQuestion().transform()


    override suspend fun getInformationScreen(): Flow<Information> {
        TODO("Not yet implemented")
    }

    override suspend fun addInformation(information: Information) {
        playerInit = try{
            information as PlayerInit
        }catch (e: ClassCastException){
            PlayerInit(UNNAMED)
        }
    }


}