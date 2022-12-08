package com.hjk.advent22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day08Test : BaseTest() {

    @Test
    fun part1() {
        Assertions.assertEquals(1870, Day08.part1(input))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(517440, Day08.part2(input))
    }
}
