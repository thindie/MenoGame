package com.example.thindie.menogame2.domain.useCase

import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.SendAble
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRecieveDataUseCase @Inject constructor(private val repository: DomainRepository) {
    suspend operator fun invoke(): Flow<SendAble> {
        return repository.recieveData()
    }
}