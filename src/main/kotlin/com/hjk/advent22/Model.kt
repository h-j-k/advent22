package com.hjk.advent22

data class Point2d(val x: Int, val y: Int)

fun List<String>.pointIndices() = indices.flatMap { y -> this[0].indices.map { x -> Point2d(x = x, y = y) } }

operator fun List<String>.get(point: Point2d) = this[point.y][point.x]
