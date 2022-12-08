package com.hjk.advent22

object Day07 {

    fun part1(input: List<String>): Int =
        process(input).getDirs().sumOf { dir -> dir.size.takeIf { it <= 100000 } ?: 0 }

    fun part2(input: List<String>): Int {
        val root = process(input)
        val spaceRequired = 30000000 - (70000000 - root.size)
        return root.getDirs().filter { it.size >= spaceRequired }.minOf { it.size }
    }

    private fun process(input: List<String>): Dir {
        var current: Dir? = null
        for (line in input) {
            when {
                line.startsWith("$ cd") -> {
                    when (val name = line.split(" ").last()) {
                        ".." -> current = current!!.parent!!
                        else -> {
                            val dir = Dir(name, current)
                            if (current != null) current += dir
                            current = dir
                        }
                    }
                }

                line.startsWith("$ ls") || line.startsWith("dir ") -> continue
                else -> line.split(" ").let { (size, name) ->
                    require(current != null)
                    current += File(name, size.toInt())
                }
            }
        }
        return generateSequence(current) { it.parent }.last()
    }

    private sealed interface Node {
        val name: String
        val size: Int
    }

    private data class Dir(override val name: String, val parent: Dir? = null) : Node {
        private val entries = mutableListOf<Node>()

        fun getDirs(): List<Dir> = listOf(this) + entries.filterIsInstance<Dir>().flatMap { it.getDirs() }

        operator fun plusAssign(entry: Node) {
            entries += entry
        }

        override val size: Int get() = entries.sumOf { it.size }
    }

    private data class File(override val name: String, override val size: Int) : Node
}
