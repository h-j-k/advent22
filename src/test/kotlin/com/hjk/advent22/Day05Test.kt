package com.hjk.advent22

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day05Test : BaseTest() {

    @Test
    fun part1() {
        assertEquals("SVFDLGLWV", Day05.part1(input))
    }

    @Test
    fun part2() {
        assertEquals("DCVTCVPCL", Day05.part2(input))
    }
}
