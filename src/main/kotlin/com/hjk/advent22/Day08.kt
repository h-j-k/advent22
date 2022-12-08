package com.hjk.advent22

object Day08 {

    fun part1(input: List<String>): Int =
        input.pointIndices().count { point ->
            input.isBorder(point) || setOf(
                input.leftOf(point),
                input.rightOf(point),
                input.topOf(point),
                input.bottomOf(point)
            ).any { points -> points.all { it < input[point] } }
        }

    fun part2(input: List<String>): Int =
        input.pointIndices().maxOf { point ->
            if (input.isBorder(point)) 0 else (input[point]..'9').toList().toCharArray().let {
                setOf(
                    input.leftOf(point).reversed().viewAny(it) ?: point.x,
                    input.rightOf(point).viewAny(it) ?: (input[0].length - point.x - 1),
                    input.topOf(point).reversed().viewAny(it) ?: point.y,
                    input.bottomOf(point).viewAny(it) ?: (input.size - point.y - 1)
                ).reduce(Int::times)
            }
        }

    private fun List<String>.isBorder(p: Point2d): Boolean = p.x == 0 || p.x == this[0].lastIndex || p.y == 0 || p.y == this.lastIndex
    private fun List<String>.leftOf(p: Point2d) = this[p.y].substring(0, p.x)
    private fun List<String>.rightOf(p: Point2d) = this[p.y].substring(p.x + 1)
    private fun List<String>.topOf(p: Point2d) = (0 until p.y).joinToString("") { this[it][p.x].toString() }
    private fun List<String>.bottomOf(p: Point2d) = ((p.y + 1) until this.size).joinToString("") { this[it][p.x].toString() }
    private fun String.viewAny(chars: CharArray): Int? = indexOfAny(chars).takeIf { it >= 0 }?.let { it + 1 }
}
