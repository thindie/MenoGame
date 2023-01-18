package com.example.thindie.menogame2.data.engine.logic

import com.example.thindie.menogame2.data.engine.EngineLogicRepository
import com.example.thindie.menogame2.domain.entities.GameResult

class ResultLogic constructor(
    private val engineLogicRepository: EngineLogicRepository,
    private val startTime: Long
) {

    fun calculateAndGet(currentTime: Long, result: GameResult<Long>) {

    }

    companion object {
        fun build(
            engineLogicRepository: EngineLogicRepository,
        ): ResultLogic {
            return ResultLogic(engineLogicRepository, System.currentTimeMillis())
        }
    }

    private fun calculateOnTime() {}
}