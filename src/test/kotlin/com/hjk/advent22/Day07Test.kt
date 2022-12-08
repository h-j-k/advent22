package com.hjk.advent22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day07Test : BaseTest() {

    @Test
    fun part1() {
        Assertions.assertEquals(1297683, Day07.part1(input))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(5756764, Day07.part2(input))
    }
}
