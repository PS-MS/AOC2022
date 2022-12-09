package day04

import readInput

fun main() {
    fun String.toIntRange(): IntRange {
        val (min, max) = this.split("-").map { it.toInt() }
        return IntRange(min, max)
    }

    operator fun IntRange.contains(other: IntRange): Boolean {
        return (this.min() >= other.min() && this.max() <= other.max() ||
                other.min() >= this.min() && other.max() <= this.max())
    }

    fun IntRange.overlaps(other: IntRange): Boolean {
        val otherList = other.toList()
        return this.find { otherList.contains(it) } != null
    }

    fun part1(input: List<String>): Int {
        return input.map { it.split(",") }.filter {
            val (first, second) = it
            val firstRange = first.toIntRange()
            val secondRange = second.toIntRange()
            firstRange in secondRange
        }.size
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split(",") }.filter {
            val (first, second) = it
            val firstRange = first.toIntRange()
            val secondRange = second.toIntRange()
            firstRange.overlaps(secondRange)
        }.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day04/test")
    val p1Test = part1(testInput)
    println("p1 test = $p1Test")
    check(p1Test == 2)

    val p2Test = part2(testInput)
    println("p2 test = $p2Test")
    check(part2(testInput) == 4)

    val input = readInput("day04/real")
    val p1Real = part1(input)
    println("p1 real = $p1Real")

    val p2Real = part2(input)
    println("p2 real = $p2Real")
}