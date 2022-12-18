package com.aoc.decoders

import com.aoc.enums.GameInputs
import com.aoc.exceptions.DecodingException
import jakarta.inject.Singleton

@Singleton
class Day2Decoder {
    fun decodeStringInput(line: String) : MutableCollection<GameInputs> {
        if (line.length < 3) {
            return ArrayList()
        }

        val splitter = line.split(" ")
        val mutableGameList = mutableListOf<GameInputs>()
        mutableGameList.add(decodeCharInput(splitter[0]))
        mutableGameList.add(decodeCharInput(splitter[1]))

        return mutableGameList
    }

    private fun decodeCharInput(input: String) : GameInputs {
        val text = input.lowercase()

        if (text == "a" || text == "x") {
            return GameInputs.ROCK
        }

        if (text == "b" || text == "y") {
            return GameInputs.PAPER
        }

        if (text == "c" || text == "z") {
            return GameInputs.SCISSORS
        }

        throw DecodingException("Unknown input of $input")
    }
}