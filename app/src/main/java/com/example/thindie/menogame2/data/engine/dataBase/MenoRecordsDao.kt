package com.example.thindie.menogame2.data.engine.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenoRecordsDao {

    @Query("SELECT * FROM menoRecords ORDER by score DESC")
    suspend fun getRecords(): List<MenoRecordDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecord(recordDbModel: MenoRecordDbModel)

    @Query("DELETE FROM menoRecords WHERE id=:recordId")
    suspend fun deleteRecord(recordId: Int)
}