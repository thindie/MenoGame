package com.example.thindie.menogame2.domain.useCase

import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.SendAble
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRecieveDataUseCase @Inject constructor(private val repository: DomainRepository) {
    suspend operator fun invoke(): Flow<SendAble> {
        return repository.sendData().flowOn(
            Dispatchers.IO
        )
    }
}