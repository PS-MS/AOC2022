package day05

import java.io.File

fun main() {
    fun part1(input: String): String {
        val (boxes, commandLines) = input.split("\r\n\r\n")
        val columns = boxes.lines().map { it.chunked(4) }.map { it.map { box -> box.trim() } }
        val columnLists: MutableList<MutableList<String>> = mutableListOf()
        columns.last().forEach { _ ->
            columnLists.add(mutableListOf())
        }
        columns.reversed().forEachIndexed { rowIndex, row ->
            if(rowIndex != 0) {
                row.forEachIndexed { index, box ->
                    if (box.isNotBlank()) {
                        columnLists[index].add(box)
                    }
                }
            }
        }
        commandLines.lines().forEach {
            val (x, y, z) = it.split(" ").mapNotNull { value -> value.toIntOrNull() }
            //move x from y to z
            repeat(x) {
                val box = columnLists[y - 1].removeLast()
                columnLists[z - 1].add(box)
            }
        }

         return String(columnLists.map { it.last()[1] }.toCharArray())
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = File("src", "day05/test.txt").readText()
    val p1Test = part1(testInput)
    println("p1 test = $p1Test")
//    check(p1Test == 2)
//
//    val p2Test = part2(testInput)
//    println("p2 test = $p2Test")
//    check(part2(testInput) == 4)
//
    val input = File("src", "day05/real.txt").readText()
    val p1Real = part1(input)
    println("p1 real = $p1Real")
//
//    val p2Real = part2(input)
//    println("p2 real = $p2Real")
}