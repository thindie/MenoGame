package com.example.thindie.menogame2.di

import com.example.thindie.menogame2.data.engine.logic.EngineRepoImpl
import com.example.thindie.menogame2.data.engine.logic.QuestionLogic
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModuleProvider {
    @Provides
    fun provideEngineRepoImpl(): EngineRepoImpl {
        return EngineRepoImpl
    }

    @Provides
    fun provideQuestionLogic(): QuestionLogic{
        return QuestionLogic()
    }
}