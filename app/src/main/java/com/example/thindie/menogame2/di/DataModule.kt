package com.example.thindie.menogame2.di

import android.app.Application
import com.example.thindie.menogame2.data.engine.dataBase.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Provides
    fun provideAppDataBase(application: Application): AppDataBase {
        return AppDataBase.getInstance(application)
    }
}