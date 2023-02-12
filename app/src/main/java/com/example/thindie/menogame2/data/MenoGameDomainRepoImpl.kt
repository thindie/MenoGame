package com.example.thindie.menogame2.data

import android.annotation.SuppressLint
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
import javax.inject.Singleton

private const val UNNAMED = "unnamed"
private const val SCORE = 2000

@Singleton
class MenoGameDomainRepoImpl @Inject constructor(
    private val menoRecordsDao: MenoRecordsDao, private val nameDao: NameDao
) : DomainRepository {

    override lateinit var gameRoundBuilder: GameRoundBuilder
    private lateinit var playerInit: PlayerInit
    private lateinit var playerRecord: PlayerRecord


    override suspend fun getPlayScreen(isNewGame: Boolean, isMaster: Boolean): GameRound {
        if (isNewGame) {
             GameRoundBuilder.build(this, isMaster)
        }
        return gameRoundBuilder.generateQuestion().transform()
    }


    @SuppressLint("SuspiciousIndentation")
    override suspend fun getInformationScreen(isShowRecords : Boolean): Flow<Information> {

        playerRecord = gameRoundBuilder.buildResult(playerInit)
            if (playerRecord.scoreInformation.toInt() > SCORE) {
                try {
                    menoRecordsDao.getRecords().forEach {
                        if(it.name == playerRecord.playerName){
                            if (it.score.toInt() <= playerRecord.scoreInformation.toInt()) {
                                menoRecordsDao.deleteRecord(it.id)
                                menoRecordsDao.saveRecord(playerRecord.map())
                            }
                        }
                        else {menoRecordsDao.saveRecord(playerRecord.map())}
                    }
                } catch (e: Exception) {
                    menoRecordsDao.saveRecord(playerRecord.map())
                }
            }
        if(isShowRecords){
            return flow{
                menoRecordsDao.getRecords().map {
                    emit(it.map())
                }
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