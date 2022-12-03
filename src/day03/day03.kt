package day03

import readInput

fun main() {
    val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()

    fun part1(input: List<String>): Int {
        return input.sumOf {
            val one = it.substring(0, it.length / 2)
            val two = it.substring(it.length / 2)
            val sharedIndex = one.indexOfAny(two.toCharArray())
            alphabet.indexOf(one[sharedIndex]) + 1
        }
    }

    fun part2(input: List<String>): Int {
        return input.windowed(3,3).sumOf { group ->
            val (a,b,c) = group
            val sharedIndex = a.indexOfFirst { aChar -> b.indexOfFirst { it == aChar } != -1 && c.indexOfFirst { it == aChar } != -1 }
            alphabet.indexOf(a[sharedIndex]) + 1
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/test")
    println("testInput 1")
    println(part1(testInput))
    println("testInput 2")
    println(part2(testInput))
    check(part1(testInput) == 157)

    val input = readInput("day03/real")
    println(part1(input))
    check(part1(input) == 7793)
    println(part2(input))
    check(part2(input) == 2499)
}