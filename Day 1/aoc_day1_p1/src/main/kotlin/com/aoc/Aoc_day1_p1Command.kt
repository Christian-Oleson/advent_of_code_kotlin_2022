package com.aoc

import io.micronaut.configuration.picocli.PicocliRunner
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import jakarta.inject.Inject
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.io.BufferedReader
import java.io.File

@Command(name = "aoc_day1_p1", description = ["..."],
        mixinStandardHelpOptions = true)
class Aoc_day1_p1Command : Runnable {

    @Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose : Boolean = false

    @Client("https://adventofcode.com/2022/day/1/input")
    @Inject
    var httpClient: HttpClient? = null

    override fun run() {
        val bufferedReader: BufferedReader = File("C:\\import\\input.txt").bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val allInput = inputString.replace("\r", "").split("\n").toTypedArray()
        var sumOfCalories = 0;
        var maxCaloricSum = 0;

        for (i in 0 until allInput.count()) {
            if (allInput[i].isNotEmpty()) {
                sumOfCalories += allInput[i].trim().toInt()
            } else {
                if (sumOfCalories > maxCaloricSum) {
                    maxCaloricSum = sumOfCalories
                }
                sumOfCalories = 0
            }
        }

        println("The elf with the most calories had $maxCaloricSum calories")
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            PicocliRunner.run(Aoc_day1_p1Command::class.java, *args)
        }
    }
}


/* Tried to make the HTTP call initially, but input is unique to user and requires an unknown auth info
val httpRequest = HttpRequest
    .create<Any>(
        HttpMethod.GET,
        "https://adventofcode.com/2022/day/1/input"
    )
    .accept("text/plain")

var result =
    httpClient!!.toBlocking().retrieve(httpRequest).split("\n".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray();

 */