package com.hjk.advent22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day15Test : BaseTest() {

    @Test
    fun part1() {
        Assertions.assertEquals(5083287L, Day15.part1(input, 2000000))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(13134039205729L, Day15.part2(input, 4000000))
    }
}
