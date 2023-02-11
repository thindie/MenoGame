package com.example.thindie.menogame2.data.engine.nameDataBase

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NameSaveDbModel::class], version = 1, exportSchema = false)
abstract class NameDataBase : RoomDatabase() {

    abstract fun nameDao(): NameDao

    companion object {
        private var INSTANCE: NameDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "nameDb.db"

        fun getInstance(application: Application): NameDataBase {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }
            }
            val db = Room.databaseBuilder(
                application,
                NameDataBase::class.java,
                DB_NAME
            ).build()
            INSTANCE = db
            return db
        }
    }
}