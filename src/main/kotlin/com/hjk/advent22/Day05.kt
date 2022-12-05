package com.hjk.advent22

object Day05 {

    fun part1(input: List<String>): String = process(input) { it.reversed() }

    fun part2(input: List<String>): String = process(input) { it }

    private fun process(input: List<String>, mapper: (String) -> String): String {
        val (stacks, instructions) = input.indexOf("").let { input.take(it) to input.drop(it + 1) }
        return instructions.mapNotNull(Move::parse).fold(convertStacks(stacks)) { acc, move ->
            val from = acc[move.from - 1]
            val to = acc[move.to - 1]
            acc[move.from - 1] = from.dropLast(move.n)
            acc[move.to - 1] = to + mapper(from.takeLast(move.n))
            acc
        }.joinToString(separator = "") { it[it.lastIndex].toString() }
    }

    private fun convertStacks(stacks: List<String>): MutableList<String> =
        (1..stacks.last().count { it.isDigit() })
            .map { (it - 1) * 4 + 1 }
            .map { i ->
                List(stacks.size - 1) { j ->
                    stacks[stacks.lastIndex - 1 - j].getOrNull(i) ?: ' '
                }.filter { it.isLetter() }.joinToString(separator = "")
            }.toMutableList()

    private data class Move(val n: Int, val from: Int, val to: Int) {
        companion object {
            private val pattern = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

            fun parse(instruction: String): Move? =
                pattern.matchEntire(instruction)?.destructured
                    ?.let { (n, from, to) -> Move(n = n.toInt(), from = from.toInt(), to = to.toInt()) }
        }
    }
}
