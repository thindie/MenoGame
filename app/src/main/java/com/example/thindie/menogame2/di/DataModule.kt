package com.example.thindie.menogame2.di

import com.example.thindie.menogame2.data.engine.EngineLogicRepository
import com.example.thindie.menogame2.data.engine.EngineRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindEngineLogicRepository(impl: EngineRepoImpl): EngineLogicRepository
}