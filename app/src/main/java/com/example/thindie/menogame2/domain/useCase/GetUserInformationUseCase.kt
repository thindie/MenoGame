package com.example.thindie.menogame2.domain.useCase

import com.example.thindie.menogame2.di.DispatchersModule
import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.abstractions.Information
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(
    private val repository: DomainRepository,
    @DispatchersModule.IODispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<Information> {
        return repository.getInformationScreen().flowOn(ioDispatcher)
    }
}