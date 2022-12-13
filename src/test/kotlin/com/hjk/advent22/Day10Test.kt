package com.hjk.advent22

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day10Test : BaseTest() {

    @Test
    fun part1() {
        Assertions.assertEquals(14860, Day10.part1(input))
    }

    @Test
    fun part2() {
        Assertions.assertEquals(listOf(
            "###   ##  #### #### #  # #  # ###  #  # ",
            "#  # #  #    # #    #  # #  # #  # # #  ",
            "#  # #      #  ###  #### #  # #  # ##   ",
            "###  # ##  #   #    #  # #  # ###  # #  ",
            "# #  #  # #    #    #  # #  # # #  # #  ",
            "#  #  ### #### #### #  #  ##  #  # #  # ",
            " "
        ), Day10.part2(input))
    }
}
