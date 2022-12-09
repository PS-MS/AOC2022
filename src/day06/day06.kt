package day06

import readInput

fun main() {
    fun parseInput(input: String, window: Int): Int {
        return input.windowed(window, 1, false).indexOfFirst {
            val theSet = it.toSet()
//            println(theSet)
            theSet.size == window
        } + window
    }

    fun part1(input: String): Int {
        return parseInput(input, 4)
    }

    fun part2(input: String): Int {
        return parseInput(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day06/test02")[0]
    val p1Test = part1(testInput)
    println("p1 test = $p1Test")
    check(p1Test == 5)

    val p2Test = part2(testInput)
    println("p2 test = $p2Test")
    check(part2(testInput) == 23)

    val input = readInput("day06/real")[0]
    val p1Real = part1(input)
    println("p1 real = $p1Real")

    val p2Real = part2(input)
    println("p2 real = $p2Real")
}