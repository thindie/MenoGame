package com.example.thindie.menogame2.data.engine.nameDataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NameDao {

    @Query("SELECT * FROM nameBuffer ORDER by name DESC")
   suspend fun getName() :  NameSaveDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveName(name: NameSaveDbModel)
}