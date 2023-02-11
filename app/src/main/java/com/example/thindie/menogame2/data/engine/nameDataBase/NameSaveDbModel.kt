package com.example.thindie.menogame2.data.engine.nameDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nameBuffer")
data class NameSaveDbModel(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,
    val name: String,

    )