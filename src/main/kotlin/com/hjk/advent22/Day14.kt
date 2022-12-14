package com.hjk.advent22

object Day14 {

    fun part1(input: List<String>): Int = process(toRocks(input))

    fun part2(input: List<String>): Int = toRocks(input).let { rocks ->
        val floor = Point2d(x = 0, y = rocks.maxOf { it.y } + 2)
        rocks.fold(Int.MAX_VALUE to Int.MIN_VALUE) { (min, max), rock ->
            min.coerceAtMost(rock.x) to max.coerceAtLeast(rock.x)
        }.let { (min, max) -> process(rocks + (floor.copy(x = min - 200)..floor.copy(x = max + 200))) }
    }

    private fun toRocks(input: List<String>): Set<Point2d> =
        input.flatMap { line ->
            line.split(" -> ")
                .map { point -> point.split(",").let { (x, y) -> Point2d(x = x.toInt(), y = y.toInt()) } }
                .fold(listOf<Point2d>()) { acc, point -> acc + (acc.lastOrNull()?.let { it..point } ?: listOf(point)) }
        }.toSet()

    private operator fun Point2d.rangeTo(other: Point2d): List<Point2d> =
        when {
            this.x == other.x -> (minOf(this.y, other.y) until maxOf(this.y, other.y)).map { this.copy(y = it) }
            this.y == other.y -> (minOf(this.x, other.x) until maxOf(this.x, other.x)).map { this.copy(x = it) }
            else -> throw RuntimeException()
        } + other

    private fun process(rocks: Set<Point2d>): Int {
        val source = Point2d(x = 500, y = 0)
        val sand = mutableSetOf<Point2d>()
        while (source !in sand) findEndPosition(source, rocks + sand)?.let { sand += it } ?: break
        return sand.size
    }

    private fun findEndPosition(source: Point2d, obstacles: Set<Point2d>): Point2d? {
        val end = obstacles.asSequence().filter { it.x == source.x && it.y >= source.y }.minByOrNull { it.y }
        val (left, right) = end?.let { it.copy(x = it.x - 1) to it.copy(x = it.x + 1) } ?: return null
        return when {
            left in obstacles && right in obstacles -> end.let { it.copy(y = it.y - 1) }
            left !in obstacles -> findEndPosition(left, obstacles)
            right !in obstacles -> findEndPosition(right, obstacles)
            else -> null
        }
    }
}
