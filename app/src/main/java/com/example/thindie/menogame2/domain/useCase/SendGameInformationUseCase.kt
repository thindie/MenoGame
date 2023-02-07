package com.example.thindie.menogame2.domain.useCase

import com.example.thindie.menogame2.domain.DomainRepository
import javax.inject.Inject

class SendGameInformationUseCase @Inject constructor(private val domainRepository: DomainRepository) {
    suspend operator fun invoke() {
        domainRepository
    }
}