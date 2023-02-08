package com.example.thindie.menogame2.di

import android.app.Application
import com.example.thindie.menogame2.data.engine.dataBase.AppDataBase
import com.example.thindie.menogame2.data.engine.dataBase.MenoRecordsDao
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
    fun provideMenoDao(appDataBase: AppDataBase): MenoRecordsDao{
        return appDataBase.menoDao()
    }
}