package com.example.thindie.menogame2.domain.useCase

import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.SendAble
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserSendDataUseCase @Inject constructor (private val domainRepository: DomainRepository){
    suspend operator fun invoke(sendAble: Flow<SendAble>){
        domainRepository.sendData(sendAble)
    }
}