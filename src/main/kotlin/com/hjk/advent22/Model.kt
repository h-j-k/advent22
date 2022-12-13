package com.hjk.advent22

data class Point2d(val x: Int, val y: Int)

fun List<String>.pointIndices() = indices.flatMap { y -> this[0].indices.map { x -> Point2d(x = x, y = y) } }

operator fun List<String>.get(point: Point2d) = this[point.y][point.x]

fun List<String>.chunkedByEmptyLine(): List<List<String>> = fold(mutableListOf(mutableListOf<String>())) { acc, line ->
    acc[acc.lastIndex] += line
    if (line.isEmpty()) acc.add(mutableListOf())
    acc
}.map { it.toList() }

fun List<String>.asAsciiMap(): Map<Point2d, Char> =
    indices.flatMap { y -> this[0].indices.map { x -> Point2d(x = x, y = y) to this[y][x] } }.toMap()

fun Point2d.allNeighbors() = (-1..1).flatMap { y -> (-1..1).map { x -> x to y } }
    .mapNotNull { (x, y) -> copy(x = this.x + x, y = this.y + y).takeIf { it != this } }

fun Point2d.sideNeighbors() = listOf(
    copy(x = this.x - 1), copy(x = this.x + 1), copy(y = this.y - 1), copy(y = this.y + 1)
)
