package com.example.thindie.menogame2.domain.useCase

import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.SendAble
import javax.inject.Inject

class UserRecieveDataUseCase @Inject constructor(private val repository: DomainRepository) {
    operator suspend fun invoke(): SendAble {
        return repository.recieveData()
    }
}