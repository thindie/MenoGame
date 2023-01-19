package com.example.thindie.menogame2.data.engine.logic

import com.example.thindie.menogame2.data.engine.EngineLogicRepository
import com.example.thindie.menogame2.domain.entities.GameResult

class ResultLogic constructor(
    private val engineLogicRepository: EngineLogicRepository,
    private val startTime: Long
) {
    private val _list: MutableList<GameResult<Long>> = mutableListOf()
    val resultList: List<GameResult<Long>>
        get() = _list


    fun setAndCalculate(result: GameResult<Long>) {
        _list.add(result)
    }


    companion object {
        fun build(
            engineLogicRepository: EngineLogicRepository
        ): ResultLogic {
            return ResultLogic(engineLogicRepository, System.currentTimeMillis())
        }
    }


}