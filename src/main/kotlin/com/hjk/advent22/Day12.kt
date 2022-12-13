package com.hjk.advent22

import java.util.PriorityQueue

object Day12 {

    fun part1(input: List<String>): Int =
        process(input) { map -> map.mapNotNull { (p, char) -> p.takeIf { char == 'S' } } }

    fun part2(input: List<String>): Int =
        process(input) { map -> map.mapNotNull { (p, char) -> p.takeIf { char in "Sa" } } }

    private fun process(input: List<String>, start: (Map<Point2d, Char>) -> List<Point2d>): Int =
        input.asAsciiMap().let { map -> start(map).minOf { process(map, it) } }

    private fun process(map: Map<Point2d, Char>, start: Point2d): Int {
        val end = map.toList().single { (_, char) -> char == 'E' }.first
        val queue = PriorityQueue<Pair<Point2d, Int>>(compareBy { it.second }).also { it.add(start to 0) }
        val seen = mutableMapOf<Point2d, Int>()
        while (queue.isNotEmpty()) {
            val (current, steps) = queue.remove()
            if (current == end) return steps
            for (next in current.sideNeighbors()) {
                if (next in map && map.safeGet(next) - map.safeGet(current) <= 1
                    && steps + 1 < seen.getOrDefault(next, Int.MAX_VALUE)) {
                    seen[next] = steps + 1
                    queue.add(next to seen.getValue(next))
                }
            }
        }
        return Int.MAX_VALUE
    }

    private fun Map<Point2d, Char>.safeGet(point: Point2d) =
        when (val p = getValue(point)) {
            'S' -> 'a'
            'E' -> 'z'
            else -> p
        }
}
