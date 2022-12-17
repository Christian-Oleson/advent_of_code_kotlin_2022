package com.aoc

import io.micronaut.configuration.picocli.PicocliRunner

import picocli.CommandLine.Command
import picocli.CommandLine.Option
import jakarta.inject.Inject
import java.io.BufferedReader
import java.io.File

@Command(name = "aoc_day1_p2", description = ["..."],
        mixinStandardHelpOptions = true)
class Aoc_day1_p2Command : Runnable {

    @Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose : Boolean = false

    override fun run() {
        val bufferedReader: BufferedReader = File("C:\\import\\input.txt").bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val allInput = inputString.replace("\r", "").split("\n").toTypedArray()
        var sumOfCalories = 0
        var maxCaloricSumOfElf1 = 0
        var maxCaloricSumOfElf2 = 0;
        var maxCaloricSumOfElf3 = 0;

        for (i in 0 until allInput.count()) {
            if (allInput[i].isNotEmpty()) {
                sumOfCalories += allInput[i].trim().toInt()
            } else {
                if (sumOfCalories > maxCaloricSumOfElf1) {
                    maxCaloricSumOfElf3 = maxCaloricSumOfElf2
                    maxCaloricSumOfElf2 = maxCaloricSumOfElf1
                    maxCaloricSumOfElf1 = sumOfCalories
                } else if (sumOfCalories > maxCaloricSumOfElf2) {
                    maxCaloricSumOfElf3 = maxCaloricSumOfElf2
                    maxCaloricSumOfElf2 = sumOfCalories
                } else if (sumOfCalories > maxCaloricSumOfElf3) {
                    maxCaloricSumOfElf3 = sumOfCalories
                }
                sumOfCalories = 0
            }
        }

        val maxThree = maxCaloricSumOfElf1 + maxCaloricSumOfElf2 + maxCaloricSumOfElf3

        println("The top 3 elves with the most calories had $maxThree calories")
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            PicocliRunner.run(Aoc_day1_p2Command::class.java, *args)
        }
    }
}
