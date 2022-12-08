package com.hjk.advent22

object Day08 {

    fun part1(input: List<String>): Int =
        input.pointIndices().count { point ->
            point.x in setOf(0, input[0].lastIndex) || point.y in setOf(0, input.lastIndex) || setOf(
                input leftOf point,
                input rightOf point,
                input topOf point,
                input bottomOf point
            ).any { points -> points.all { it < input[point] } }
        }

    fun part2(input: List<String>): Int =
        input.pointIndices().maxOf { point ->
            val heights = (input[point]..'9').toSet().toCharArray()
            mapOf(
                (input leftOf point).reversed() to point.x,
                (input rightOf point) to (input[0].length - point.x - 1),
                (input topOf point).reversed() to point.y,
                (input bottomOf point) to (input.size - point.y - 1)
            ).map { (side, default) -> (side.indexOfAny(heights) + 1).takeIf { it >= 1 } ?: default }.reduce(Int::times)
        }

    private infix fun List<String>.leftOf(p: Point2d) = this[p.y].substring(0, p.x)
    private infix fun List<String>.rightOf(p: Point2d) = this[p.y].substring(p.x + 1)
    private infix fun List<String>.topOf(p: Point2d) = (0 until p.y).column(this, p)
    private infix fun List<String>.bottomOf(p: Point2d) = ((p.y + 1) until this.size).column(this, p)
    private fun IntRange.column(input: List<String>, p: Point2d) = joinToString("") { input[it][p.x].toString() }
}
