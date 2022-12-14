package com.hjk.advent22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day14Test : BaseTest() {

    @Test
    fun part1() {
        Assertions.assertEquals(892, Day14.part1(input))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(27155, Day14.part2(input))
    }
}
