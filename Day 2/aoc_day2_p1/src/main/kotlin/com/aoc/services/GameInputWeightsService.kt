package com.aoc.services

import com.aoc.enums.GameInputs
import jakarta.inject.Singleton

@Singleton
class GameInputWeightsService {

    fun calculateRoundScore(opposingPlayerInput: GameInputs, myInput: GameInputs) : Int {
        val didIWin = compareGameInputs(opposingPlayerInput, myInput)
        val winLossScore = scoreOfWinLossOrTie(didIWin)

        return calculateWeightOfGameInput(myInput) + winLossScore
    }

    private fun calculateWeightOfGameInput(gameInput: GameInputs) : Int {
        if (gameInput == GameInputs.ROCK) {
            return 1
        }

        if (gameInput == GameInputs.PAPER) {
            return 2
        }

        return 3
    }

    private fun compareGameInputs(opposingPlayerInput: GameInputs, myInput: GameInputs) : Boolean? {
        var didIWin : Boolean? = false

        if (calculateTie(opposingPlayerInput, myInput)) {
            return null
        }

        return calculateWin(opposingPlayerInput, myInput)
    }

    private fun calculateTie(opposingPlayerInput: GameInputs, myInput: GameInputs) : Boolean {
        if ((opposingPlayerInput == GameInputs.PAPER && myInput == GameInputs.PAPER)
            || (opposingPlayerInput == GameInputs.ROCK && myInput == GameInputs.ROCK)
            || (opposingPlayerInput == GameInputs.SCISSORS && myInput == GameInputs.SCISSORS)) {
            return true
        }

        return false
    }

    private fun calculateWin(opposingPlayerInput: GameInputs, myInput: GameInputs) : Boolean {
        if ((opposingPlayerInput == GameInputs.PAPER && myInput == GameInputs.SCISSORS)
            || (opposingPlayerInput == GameInputs.ROCK && myInput == GameInputs.PAPER)
            || (opposingPlayerInput == GameInputs.SCISSORS && myInput == GameInputs.ROCK)) {
            return true
        }

        return false
    }

    private fun scoreOfWinLossOrTie(didIWin: Boolean?) : Int {
        if (didIWin == null) {
            return 3
        }

        if (didIWin) {
            return 6
        }

        return 0
    }
}