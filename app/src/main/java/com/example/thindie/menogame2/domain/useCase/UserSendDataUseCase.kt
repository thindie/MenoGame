package com.example.thindie.menogame2.domain.useCase

import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.SendAble
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserSendDataUseCase @Inject constructor(private val domainRepository: DomainRepository) {
    suspend operator fun invoke(sendAble: Flow<SendAble>) {
        domainRepository.recieveData(sendAble.flowOn(Dispatchers.Default))
    }
}