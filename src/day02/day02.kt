package day02

import readInput

sealed class RockPaperScissors(val value: Int) {
    abstract infix fun beats(other: RockPaperScissors): Boolean
    abstract fun fixResult(x: String): RockPaperScissors?

    class Rock : RockPaperScissors(1) {
        override fun beats(other: RockPaperScissors): Boolean {
            return other is Scissors
        }

        override fun fixResult(x: String): RockPaperScissors? {
            return when (x) {
                "Y" -> Rock() // draw
                "X" -> Scissors()
                "Z" -> Paper()
                else -> null
            }
        }
    }

    class Paper : RockPaperScissors(2) {
        override fun beats(other: RockPaperScissors): Boolean {
            return other is Rock
        }

        override fun fixResult(x: String): RockPaperScissors? {
            return when (x) {
                "Y" -> Paper()
                "X" -> Rock()
                "Z" -> Scissors()
                else -> null
            }
        }
    }

    class Scissors : RockPaperScissors(3) {
        override fun beats(other: RockPaperScissors): Boolean {
            return other is Paper
        }

        override fun fixResult(x: String): RockPaperScissors? {
            return when (x) {
                "Y" -> Scissors() // draw
                "X" -> Paper() // lose
                "Z" -> Rock() // win
                else -> null
            }
        }
    }
}

fun main() {
    infix fun RockPaperScissors.versus(other: RockPaperScissors): Int {
        val result = when {
            this beats other -> 6
            other beats this -> 0
            else -> 3
        }
        return result + this.value
    }

    fun String.toRockPaperScissors(): RockPaperScissors? {
        return when (this) {
            "A","X" -> RockPaperScissors.Rock()
            "B","Y" -> RockPaperScissors.Paper()
            "C","Z" -> RockPaperScissors.Scissors()
            else -> null
        }
    }

    fun calculateScore(a: String, b: String): Int {
        val them = a.toRockPaperScissors()
        val us = them?.fixResult(b)

        return if(them != null && us != null) {
            return us versus them
        } else 0
    }

    fun parseRows(input: List<String>): List<List<String>> {
        return input.map { rounds -> rounds.split(" ")}
    }

    fun part1(input: List<String>): Int {
        val rounds = parseRows(input).map { it.mapNotNull { it.toRockPaperScissors() } }
        return rounds.sumOf { val (a,b) = it; b versus a }
    }

    fun part2(input: List<String>): Int {
        val rounds = parseRows(input)
        return rounds.sumOf { val (a,b) = it; calculateScore(a, b) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/test")
    check(part1(testInput) == 15)

    val input = readInput("day02/real")
    println(part1(input))
    println(part2(input))
}