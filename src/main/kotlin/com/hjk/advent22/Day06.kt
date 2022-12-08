package com.hjk.advent22

object Day06 {

    fun part1(input: String): Int = process(input, 4)

    fun part2(input: String): Int = process(input, 14)

    private fun process(input: String, length: Int) =
        generateSequence((length - 1) to input.substring(0 until length)) { (n, _) ->
            (n + 1) to input.substring((n - length + 2)..(n + 1))
        }.first { (_, substring) -> substring.toSet().size == length }.first + 1
}
