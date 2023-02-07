package com.example.thindie.menogame2.domain.useCase

import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.GameRound
import javax.inject.Inject

class GetPlayScreenUseCase @Inject constructor(private  val domainRepository: DomainRepository) {
    suspend  operator fun invoke(): GameRound = domainRepository.getPlayScreen()

}