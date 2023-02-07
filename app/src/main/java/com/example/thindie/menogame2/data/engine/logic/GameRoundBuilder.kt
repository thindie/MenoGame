package com.example.thindie.menogame2.data.engine.logic

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.thindie.menogame2.di.DispatchersModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


private const val IN_MILLIS = 1000L
private const val IS_FRESH = 3
private const val IS_START = 6
private const val IS_SIGNIFICANT = 9
private const val IS_MASTER = 12
private const val SCORE_MULTIPLIER = 10
private const val FIELD_ON_TIME_DIVIDER = 3
private const val FIELD_ON_DIVIDER_SETTER = 8
private const val WRONG = 0
private const val RIGHT = 1


@Singleton
class GameRoundBuilder @Inject constructor(@DispatchersModule.IODispatcher val io: CoroutineDispatcher) {

    private val howLong = System.currentTimeMillis()
    private val gameTimes = listOf(IS_FRESH, IS_START, IS_SIGNIFICANT, IS_MASTER)
    private val _questions = mutableListOf<GameRoundModel>()
    val questions: List<GameRoundModel>
        get() = _questions.toList()


    private fun generateQuestion() {
        val questionPad = levelNow().gameFieldSet().prepareQuestionPad()
        _questions.add(
            GameRoundModel(
                shownScore = levelNow().times(SCORE_MULTIPLIER),
                answerTime = 0,
                showPadTime = 0,
                questionPad = questionPad,
                shownPad = questionPad.asShownPad()
            )
        )
    }

    private fun onCancel() {

    }

    private suspend fun onQuestionTriggered() {

    }



    private fun Long.timeFromStart() = System.currentTimeMillis().minus(this).div(IN_MILLIS)
    private fun Long.isTicked(howLong: Int) = this > howLong

    private val List<Int>.levelDependOnTime: () -> Int
        get() = {
            var result = IS_MASTER
            this.forEach { isAbout ->
                if (howLong.timeFromStart().isTicked(isAbout)) {
                    result = isAbout
                }
            }
            result
        }

    private fun levelNow() = run { gameTimes.levelDependOnTime() }

    private fun Int.gameFieldSet(): List<Int> {
        val listSize = this.div(FIELD_ON_TIME_DIVIDER).times(FIELD_ON_DIVIDER_SETTER)
        val gameFieldList = MutableList(listSize) { index ->
            this.onDependedWillFill(index)
        }
        return gameFieldList.toList()
    }


    private fun List<Int>.asShownPad(): List<Int> {
        val size = this.size
        this.toMutableList().clear()
        return MutableList(size) { WRONG }.toList()
    }


    private fun List<Int>.prepareQuestionPad(): List<Int> {
        this.toMutableList().shuffle()
        return this.toList()
    }

    private fun Int.onDependedWillFill(index: Int): Int {
        return if (this < index) RIGHT else WRONG
    }


}
