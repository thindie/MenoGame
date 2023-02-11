package com.example.thindie.menogame2.domain.entities

import com.example.thindie.menogame2.domain.entities.abstractions.Information

data class PlayerInit(
    override val playerName: String, override val scoreInformation: String? = null,
    override val timeInformation: String? = null,
    override val questionsQuota: String? = null
) : Information()


data class PlayerRecord(
    override val playerName: String, override val scoreInformation: String,
    override val timeInformation: String,
    override val questionsQuota: String
) : Information()

