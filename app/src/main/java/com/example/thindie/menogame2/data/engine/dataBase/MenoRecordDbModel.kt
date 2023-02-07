package com.example.thindie.menogame2.data.engine.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menoRecords")
data class MenoRecordDbModel(
    val name: String,
    val score: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)