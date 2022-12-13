package com.hjk.advent22

object Day11 {

    fun part1(input: List<String>): Long = process(input, 20, 3, false)

    fun part2(input: List<String>): Long = process(input, 10_000, 1, true)

    private fun process(input: List<String>, times: Int, divisor: Long, lcmMode: Boolean): Long {
        val monkeys = input.chunkedByEmptyLine().map(Monkey::parse).associateBy { it.index }
        val common = monkeys.values.map(Monkey::divisor).fold(1L, Long::times).let { if (lcmMode) it else it + 1 }
        repeat(times) { monkeys.values.forEach { it.process(monkeys, divisor, common) } }
        return monkeys.values.map(Monkey::itemsInspected).sortedDescending().take(2).fold(1L, Long::times)
    }

    private data class Monkey(
        val index: Int,
        val levels: MutableList<Long>,
        val mapper: (Long) -> Long,
        val divisor: Long,
        val test: (Long) -> Boolean,
        val ifTrue: Int,
        val ifFalse: Int
    ) {
        private var seen = 0L

        fun itemsInspected() = seen

        fun process(monkeys: Map<Int, Monkey>, divisor: Long, common: Long) {
            seen += levels.size
            levels.map { (mapper(it) / divisor) % common }.partition(test).let { (trueNewLevels, falseNewLevels) ->
                trueNewLevels.forEach { monkeys.getValue(ifTrue) += it }
                falseNewLevels.forEach { monkeys.getValue(ifFalse) += it }
            }
            levels.clear()
        }

        operator fun plusAssign(level: Long) {
            levels += level
        }

        companion object {
            fun parse(chunk: List<String>): Monkey {
                val (index, rest) = chunk[0] to chunk.drop(1)
                val (levels, operation, testLogic, ifTrue, ifFalse) = rest
                val (divisor, test) = convertTest(testLogic)
                return Monkey(
                    index = indexRegex.matchEntire(index)!!.destructured.let { (i) -> i.toInt() },
                    levels = levels.substringAfter(": ").split(", ").map(String::toLong).toMutableList(),
                    mapper = convertOperation(operation),
                    divisor = divisor,
                    test = test,
                    ifTrue = ifTrue.split(" ").last().toInt(),
                    ifFalse = ifFalse.split(" ").last().toInt()
                )
            }

            fun convertOperation(operation: String): (Long) -> Long =
                operationRegex.matchEntire(operation)!!.destructured.let { (operator, arg) ->
                    when (operator) {
                        "+" -> arg.toLongOrNull()?.let { operand -> { it + operand } } ?: { it + it }
                        "-" -> arg.toLongOrNull()?.let { operand -> { it - operand } } ?: { 0 }
                        "*" -> arg.toLongOrNull()?.let { operand -> { it * operand } } ?: { it * it }
                        else -> throw RuntimeException()
                    }
                }

            fun convertTest(test: String): Pair<Long, (Long) -> Boolean> =
                test.split(" ").last().toLong().let { divisor -> divisor to { it % divisor == 0L } }

            val indexRegex = "Monkey (\\d+):".toRegex()

            val operationRegex = "\\s+Operation: new = old ([*+-]) (\\d+|old)".toRegex()
        }
    }
}
