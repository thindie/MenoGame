package com.example.thindie.menogame2.di

import android.app.Application
import com.example.thindie.menogame2.data.engine.dataBase.AppDataBase
import com.example.thindie.menogame2.data.engine.dataBase.MenoRecordsDao
import com.example.thindie.menogame2.data.engine.nameDataBase.NameDao
import com.example.thindie.menogame2.data.engine.nameDataBase.NameDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideAppDataBase(application: Application): AppDataBase {
        return AppDataBase.getInstance(application)
    }

    @Provides
    fun provideMenoDao(appDataBase: AppDataBase): MenoRecordsDao {
        return appDataBase.menoDao()
    }

    @Provides
    fun provideNameDb(application: Application): NameDataBase {
        return NameDataBase.getInstance(application)
    }

    @Provides
    fun provideNameDao(appDataBase: NameDataBase): NameDao {
        return appDataBase.nameDao()
    }
}