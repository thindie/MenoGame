package com.example.thindie.menogame2.data.engine.logic

import com.example.thindie.menogame2.domain.DomainRepository
import com.example.thindie.menogame2.domain.entities.PlayerInit
import com.example.thindie.menogame2.domain.entities.PlayerRecord


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
private const val INITIAL = 1


class GameRoundBuilder(private val domainRepository: DomainRepository, isMaster: Boolean) {

    private val howLong: Long = if (!isMaster) {
        System.currentTimeMillis()
    } else System.currentTimeMillis().minus(IS_MASTER.times(IN_MILLIS))

    private val gameTimes = listOf(IS_FRESH, IS_START, IS_SIGNIFICANT, IS_MASTER)
    private val _questions = mutableListOf<GameRoundModel>()
    val questions: List<GameRoundModel>
        get() = _questions.toList()
    private var addition = INITIAL

    fun buildResult(playerInit: PlayerInit): PlayerRecord {
        return playerInit.onEndGame()
    }

    fun PlayerInit.onEndGame(): PlayerRecord {
        val timePlayed = howLong.timeFromStart().toString()
        val questionSolved = _questions.size.toString()
        val score = WRONG.withCollectFromList().toString()
        return PlayerRecord(
            this.playerName,
            scoreInformation = score,
            timeInformation = timePlayed,
            questionsQuota = questionSolved
        )
    }

    fun generateQuestion(): GameRoundModel {

        val questionPad =
            levelNow().gameFieldSet().checkAndAddAdditionalDifficulty().prepareQuestionPad()
        val showScore = levelNow().willIterateScore(SCORE_MULTIPLIER)
        if (questions.isEmpty()) {
            _questions.add(
                GameRoundModel(
                    shownScore = WRONG,
                    answerTime = levelNow().setAnswerTime(),
                    questionPad = questionPad,
                    howManyAnswers = questionPad.howManyAnswers()
                )
            )
            return questions.last()
        } else {
            _questions.add(
                GameRoundModel(
                    shownScore = showScore,
                    answerTime = levelNow().setAnswerTime(),
                    questionPad = questionPad,
                    howManyAnswers = questionPad.howManyAnswers()
                )
            )
        }
        return questions.last().copy(shownScore = showScore.withCollectFromList())
    }

    private fun Int.setAnswerTime() = run { (this.div(TIME_DIVIDER)).toLong() }

    private fun Int.withCollectFromList(): Int {
        return this.plus(_questions.collectScore())
    }

    private fun List<Int>.checkAndAddAdditionalDifficulty(): List<Int> {
        if (levelNow() == IS_MASTER) {
            val gameFieldList = MutableList(this.size) { index ->
                levelNow().plus(addition++.div(2)).onDependedWillFill(index)
            }
            return gameFieldList.toList()
        }
        return this
    }

    private fun List<GameRoundModel>.collectScore(): Int {
        var scores = 0
        this.forEach { scores += it.shownScore }
        return scores
    }

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
        return this.times(score).minus(this)
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
        return this.filter { it == 0 }.size
    }

    companion object {
        fun build(domainRepository: DomainRepository, isMaster: Boolean) {
            domainRepository.gameRoundBuilder = GameRoundBuilder(domainRepository, isMaster)
        }
    }

}


