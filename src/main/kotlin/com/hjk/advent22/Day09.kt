package com.hjk.advent22

import kotlin.math.abs
import kotlin.math.sign

object Day09 {

    fun part1(input: List<String>): Int {
        var head = Point2d(x = 0, y = 0)
        val tailPositions = mutableListOf(head)
        for (next in input) {
            val (direction, distance) = next.split(" ").let { (a, b) -> a to b.toInt() }
            head = when (direction) {
                "U" -> head.copy(y = head.y - distance)
                "D" -> head.copy(y = head.y + distance)
                "L" -> head.copy(x = head.x - distance)
                "R" -> head.copy(x = head.x + distance)
                else -> throw RuntimeException()
            }
            tailPositions += if (!head.isNextTo(tailPositions.last()))
                (tailPositions.last()..(head.stepBefore(direction))).distinct() else emptyList()
        }
        return tailPositions.toSet().size
    }

    fun part2(input: List<String>): Int {
        val origin = Point2d(x = 0, y = 0)
        val knots = MutableList(10) { origin }
        val visited = mutableSetOf(origin)
        for (next in input) {
            val (direction, distance) = next.split(" ").let { (a, b) -> a to b.toInt() }
            repeat(distance) {
                knots[0] = knots.first().let {
                    when (direction) {
                        "U" -> it.copy(y = it.y - 1)
                        "D" -> it.copy(y = it.y + 1)
                        "L" -> it.copy(x = it.x - 1)
                        "R" -> it.copy(x = it.x + 1)
                        else -> throw RuntimeException()
                    }
                }
                (0 until knots.lastIndex).forEach {
                    val head = knots[it]
                    var tail = knots[it + 1]
                    if (tail !in head.allNeighbors()) {
                        tail = Point2d(x = tail.x + (head.x - tail.x).sign, y = tail.y + (head.y - tail.y).sign)
                        visited += knots.last()
                    }
                    knots[it + 1] = tail
                }
            }
        }
        return visited.size
    }

    private fun Point2d.isNextTo(other: Point2d): Boolean =
        this == other || maxOf(abs(this.x - other.x), abs(this.y - other.y)) == 1

    private fun Point2d.stepBefore(direction: String): Point2d =
        when (direction) {
            "U" -> copy(y = this.y + 1)
            "D" -> copy(y = this.y - 1)
            "L" -> copy(x = this.x + 1)
            "R" -> copy(x = this.x - 1)
            else -> throw RuntimeException()
        }

    private operator fun Point2d.rangeTo(target: Point2d): List<Point2d> = when {
        this.x == target.x -> when {
            this.y < target.y -> ((this.y + 1)..target.y).map { copy(y = it) }
            else -> ((this.y - 1) downTo target.y).map { copy(y = it) }
        }

        this.y == target.y -> when {
            this.x < target.x -> ((this.x + 1)..target.x).map { copy(x = it) }
            else -> ((this.x - 1) downTo target.x).map { copy(x = it) }
        }

        else -> when {
            abs(this.x - target.x) == 1 -> {
                when {
                    this.y < target.y -> target.copy(y = this.y + 1).let { listOf(it) + (it..target) }
                    else -> target.copy(y = this.y - 1).let { listOf(it) + (it..target) }
                }
            }

            abs(this.y - target.y) == 1 -> {
                when {
                    this.x < target.x -> target.copy(x = this.x + 1).let { listOf(it) + (it..target) }
                    else -> target.copy(x = this.x - 1).let { listOf(it) + (it..target) }
                }
            }

            else -> emptyList()
        }
    }
}
