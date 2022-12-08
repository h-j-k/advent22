package com.hjk.advent22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day06Test : BaseTest() {

    @Test
    fun part1() {
        Assertions.assertEquals(1275, Day06.part1(input[0]))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(3605, Day06.part2(input[0]))
    }
}
