package com.hjk.advent22

import kotlin.math.abs

object Day10 {

    fun part1(input: List<String>): Int =
        process(input).filter { (_, cycle) -> cycle in setOf(20, 60, 100, 140, 180, 220) }
            .sumOf { (x, cycle) -> x * cycle }

    fun part2(input: List<String>): List<String> = process(input).chunked(40).map { chunk ->
        chunk.fold("") { acc, (x, cycle) -> "$acc${if (abs(x - ((cycle - 1) % 40)) <= 1) "#" else " "}" }
    }.toList()

    private fun process(input: List<String>): Sequence<Cycle> =
        input.asSequence().runningFold(listOf(Cycle(x = 1, cycle = 1))) { acc, line ->
            val (x, cycle) = acc.last()
            listOfNotNull(
                Cycle(x = x, cycle = cycle + 1),
                line.split(" ").takeIf { it.size == 2 }
                    ?.let { (_, delta) -> Cycle(x = x + delta.toInt(), cycle = cycle + 2) }
            )
        }.flatten()

    private data class Cycle(val x: Int, val cycle: Int)
}
