package com.hjk.advent22

import com.fasterxml.jackson.databind.ObjectMapper

object Day13 {

    fun part1(input: List<String>): Int = input.chunkedByEmptyLine().foldIndexed(0) { index, acc, (a, b) ->
        acc + (if (isRightOrder(a.convert(), b.convert()) == true) index + 1 else 0)
    }

    fun part2(input: List<String>): Int = listOf(listOf(listOf(2)), listOf(listOf(6))).let { dividerPackets ->
        (input.filter { it.isNotEmpty() }.map { it.convert() } + dividerPackets)
            .sortedWith { a, b -> isRightOrder(a, b)?.let { if (it) -1 else 1 } ?: 0 }
            .let { sorted -> dividerPackets.fold(1) { acc, packet -> acc * (1 + sorted.indexOf(packet)) } }
    }

    private fun isRightOrder(aList: List<*>, bList: List<*>): Boolean? {
        for (index in 0..maxOf(aList.lastIndex, bList.lastIndex)) {
            val (a, b) = listOf(aList, bList).map { it.getOrNull(index) }
            val result = when {
                a == null -> true
                b == null -> false
                a is Int && b is Int -> a.takeUnless { it == b }?.let { it < b }
                a is List<*> && b is List<*> -> isRightOrder(a, b)
                a is Int && b is List<*> -> isRightOrder(listOf(a), b)
                a is List<*> && b is Int -> isRightOrder(a, listOf(b))
                else -> null
            }
            if (result != null) return result
        }
        return null
    }

    private fun String.convert(): List<*> = ObjectMapper().readValue(this, List::class.java)
}
