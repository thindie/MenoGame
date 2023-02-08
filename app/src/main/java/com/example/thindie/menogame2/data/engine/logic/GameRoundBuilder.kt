package com.example.thindie.menogame2.data.engine.logic

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton


private const val IN_MILLIS = 1000L
private const val IS_FRESH = 15
private const val IS_START = 30
private const val IS_SIGNIFICANT = 45
private const val IS_MASTER = 60
private const val SCORE_MULTIPLIER = 5
private const val FIELD_ON_TIME_DIVIDER = 15
private const val FIELD_ON_DIVIDER_SETTER = 8
private const val WRONG = 0
private const val RIGHT = 1

private const val TIME_DIVIDER = 15
private const val TIME_PARAM = 5


@Singleton
class GameRoundBuilder @Inject constructor() {

    private val howLong = System.currentTimeMillis()
    private val gameTimes = listOf(IS_FRESH, IS_START, IS_SIGNIFICANT, IS_MASTER)
    private val _questions = mutableListOf<GameRoundModel>()
    val questions: List<GameRoundModel>
        get() = _questions.toList()


    fun generateQuestion(): GameRoundModel {

        val questionPad = levelNow().gameFieldSet().prepareQuestionPad()

        _questions.add(
            GameRoundModel(
                shownScore = levelNow().willIterateScore(SCORE_MULTIPLIER),
                answerTime = levelNow().setAnswerTime(),
                questionPad = questionPad,
                howManyAnswers = questionPad.howManyAnswers()
            )
        )
        Log.d("SERVICE_TAG",  "GENERATED")
        return questions.last()
    }

    private fun Int.setAnswerTime() = run { TIME_PARAM.minus(this.div(TIME_DIVIDER)).toLong() }


    private fun levelNow() = run { gameTimes.levelDependOnTime() }

    private val List<Int>.levelDependOnTime: () -> Int
        get() = {
            var theLevel = IS_FRESH
            this.forEach { isAbout ->
                if (howLong.timeFromStart().isTicked(isAbout)) {
                    theLevel = isAbout
                }
            }
            theLevel
        }

    private fun Long.isTicked(howLong: Int) = this > howLong

    private fun Long.timeFromStart() = (System.currentTimeMillis().minus(this)).div(IN_MILLIS)
    private fun Int.gameFieldSet(): List<Int> {
        val listSize = this.div(FIELD_ON_TIME_DIVIDER).times(FIELD_ON_DIVIDER_SETTER)
        val gameFieldList = MutableList(listSize) { index ->
            this.onDependedWillFill(index)
        }
        return gameFieldList.toList()
    }

    private fun Int.willIterateScore(score: Int): Int {
        return this.times(score)
    }


    private fun List<Int>.prepareQuestionPad(): List<Int> {
        val list: MutableList<Int> = this.toMutableList()
        list.shuffle()
        return list.toList()
    }

    private fun Int.onDependedWillFill(index: Int): Int {
        return if (this.div(TIME_DIVIDER) < index) RIGHT else WRONG
    }

    private fun List<Int>.howManyAnswers(): Int {
        return this.filter { it == 0 }.size.plus(RIGHT)
    }


}
