package com.aoc.controllers

import com.aoc.decoders.Day2Decoder
import com.aoc.services.GameInputWeightsService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.validation.Validated
import io.micronaut.http.MediaType
import java.io.BufferedReader
import java.io.File

@Validated
@Controller
class Day2Controller(private val decoder: Day2Decoder, private val gameInputWeights: GameInputWeightsService) {

    @Get("/Day2Part1", produces = [MediaType.APPLICATION_JSON])
    fun day2Part1() : Any {
        val bufferedReader: BufferedReader = File("C:\\import\\day2.txt").bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val allInput = inputString.replace("\r", "").split("\n").toTypedArray()

        var totalScore = 0

        for (input in allInput) {
            val decodedInput = decoder.decodeStringInput(input)

            if (decodedInput.any()) {
                totalScore += gameInputWeights.calculateRoundScore(decodedInput.first(), decodedInput.last())
            }
        }

        println(allInput)

        return "My total score is $totalScore"
    }
}
