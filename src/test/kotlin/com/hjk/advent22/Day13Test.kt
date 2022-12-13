package com.hjk.advent22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day13Test : BaseTest() {

    @Test
    fun part1() {
        Assertions.assertEquals(5675, Day13.part1(input))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(20383, Day13.part2(input))
    }
}
