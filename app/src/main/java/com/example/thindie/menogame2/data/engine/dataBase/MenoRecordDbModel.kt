package com.example.thindie.menogame2.data.engine.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.thindie.menogame2.domain.entities.PlayerRecord


@Entity(tableName = "menoRecords")
data class MenoRecordDbModel(
    val name: String,
    val score: String,
    val questionsAnswered: String,
    val gameTime: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)


fun MenoRecordDbModel.map(): PlayerRecord {
    return PlayerRecord(
        this.name,
        scoreInformation = this.score,
        timeInformation = this.gameTime,
        questionsQuota = this.questionsAnswered
    )
}

fun PlayerRecord.map(): MenoRecordDbModel {
    return MenoRecordDbModel(
        this.playerName,
        score = this.scoreInformation,
        questionsAnswered = this.questionsQuota,
        gameTime = this.timeInformation
    )
}