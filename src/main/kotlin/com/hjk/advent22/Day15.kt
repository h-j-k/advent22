package com.hjk.advent22

import kotlin.math.abs

object Day15 {

    fun part1(input: List<String>, row: Int): Long = noBeaconsOn(input.mapNotNull(Sensor::parse), row)
        .fold(0L to Long.MIN_VALUE) { (count, last), range ->
            if (count == 0L) (range.last - last.coerceAtLeast(range.first)) to range.last else count to last
        }.first

    fun part2(input: List<String>, max: Int): Long = input.mapNotNull(Sensor::parse).let { sensors ->
        (0..max).reversed().asSequence().mapNotNull { row ->
            noBeaconsOn(sensors, row).takeIf { it.size == 2 }?.let { (a, _) -> 4000000 * (a.last + 1) + row }
        }.first()
    }

    private fun noBeaconsOn(sensors: List<Sensor>, row: Int): List<LongRange> =
        sensors.mapNotNull { it.getEmptyOnRow(row) }.sortedBy { it.first }.fold(listOf()) { acc, range ->
            if (acc.lastOrNull()?.let { it.last + 1 >= range.first } == true)
                acc.dropLast(1) + acc.last().let { setOf(it.first..(it.last.coerceAtLeast(range.last))) }
            else acc + setOf(range)
        }

    private data class Sensor(val point: Point2d, val beacon: Point2d) {
        private val distance = (point - beacon)

        fun getEmptyOnRow(row: Int): LongRange? = (distance - abs(row - point.y).toLong()).takeIf { it > 0L }
            ?.let { delta -> (point.x - delta)..(point.x + delta) }

        companion object {
            private val regex =
                "Sensor at x=([0-9-]+), y=([0-9-]+): closest beacon is at x=([0-9-]+), y=([0-9-]+)".toRegex()

            fun parse(line: String): Sensor? = regex.matchEntire(line)?.destructured?.let { (sX, sY, bX, bY) ->
                Sensor(
                    point = Point2d(x = sX.toInt(), y = sY.toInt()),
                    beacon = Point2d(x = bX.toInt(), y = bY.toInt())
                )
            }
        }
    }

    private operator fun LongRange.contains(other: LongRange) = other.first in this && other.last in this
}
