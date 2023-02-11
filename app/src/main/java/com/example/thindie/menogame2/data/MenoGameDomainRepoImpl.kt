package com.example.thindie.menogame2.data

import com.example.thindie.menogame2.data.engine.dataBase.MenoRecordsDao
import com.example.thindie.menogame2.data.engine.dataBase.map
import com.example.thindie.menogame2.data.engine.logic.GameRoundBuilder
import com.example.thindie.menogame2.data.engine.logic.transform
import com.example.thindie.menogame2.data.engine.nameDataBase.NameDao
import com.example.thindie.menogame2.data.engine.nameDataBase.map
import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.GameRound
import com.example.thindie.menogame2.domain.entities.PlayerInit
import com.example.thindie.menogame2.domain.entities.PlayerRecord
import com.example.thindie.menogame2.domain.entities.abstractions.Information
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val UNNAMED = "unnamed"
private const val SCORE = 800

class MenoGameDomainRepoImpl @Inject constructor(
    private val gameRoundBuilder: GameRoundBuilder,
    private val menoRecordsDao: MenoRecordsDao,
    private val nameDao: NameDao
) : DomainRepository {

    private lateinit var playerInit: PlayerInit
    private lateinit var playerRecord: PlayerRecord

    override suspend fun getPlayScreen(): GameRound =
        gameRoundBuilder.generateQuestion().transform()


    override suspend fun getInformationScreen(): Flow<Information> {
        playerRecord = gameRoundBuilder.buildResult(playerInit)
            if (playerRecord.scoreInformation.toInt() > SCORE) {
                val record: PlayerRecord = try {
                    menoRecordsDao.getRecords().last().map()
                } catch (e: NullPointerException) {
                    playerRecord
                }

                if (record.scoreInformation.toInt() <= playerRecord.scoreInformation.toInt()) {
                    menoRecordsDao.saveRecord(playerRecord.map())
                }
            }
         return flow {
            emit(playerRecord)
        }
    }

    override suspend fun addInformation(information: Information) {
        playerInit = try {
            information as PlayerInit

        } catch (e: ClassCastException) {
            PlayerInit(UNNAMED)
        }
        nameDao.saveName(playerInit.map())
    }

    override suspend fun initName(): String? {
        return try {
            playerInit = nameDao.getName().map()
            playerInit.playerName
        } catch (e: NullPointerException) {
            null
        }
    }

}