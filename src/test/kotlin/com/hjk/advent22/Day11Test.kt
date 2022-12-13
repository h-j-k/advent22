package com.hjk.advent22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day11Test : BaseTest() {

    @Test
    fun part1() {
        Assertions.assertEquals(69918L, Day11.part1(input))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(19573408701L, Day11.part2(input))
    }
}
