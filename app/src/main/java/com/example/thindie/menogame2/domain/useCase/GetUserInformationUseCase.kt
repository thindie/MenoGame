package com.example.thindie.menogame2.domain.useCase

import com.example.thindie.menogame2.domain.DomainRepository
import javax.inject.Inject

class GetUserInformationUseCase @Inject constructor(private val repository: DomainRepository) {
    suspend operator fun invoke(){

    }
}