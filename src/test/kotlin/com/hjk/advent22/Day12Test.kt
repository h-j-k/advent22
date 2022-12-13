package com.hjk.advent22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day12Test : BaseTest() {

    @Test
    fun part1() {
        Assertions.assertEquals(412, Day12.part1(input))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(402, Day12.part2(input))
    }
}
