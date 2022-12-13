package com.hjk.advent22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day09Test : BaseTest() {

    @Test
    fun part1() {
        Assertions.assertEquals(6367, Day09.part1(input))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(2536, Day09.part2(input))
    }
}
