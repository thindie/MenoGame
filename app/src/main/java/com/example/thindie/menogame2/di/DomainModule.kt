package com.example.thindie.menogame2.di

import com.example.thindie.menogame2.data.MenoGameDomainRepoImpl
import com.example.thindie.menogame2.domain.DomainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindDomainRepository(impl: MenoGameDomainRepoImpl): DomainRepository
}