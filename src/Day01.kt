import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var highestElf = 0
        var thisElf = 0
        input.forEachIndexed { index, value ->
            val calories = value.toIntOrNull()
            if (calories != null) {
                thisElf += calories
                if (index == input.lastIndex) {
                    highestElf = max(highestElf, thisElf)
                }
            } else {
                highestElf = max(highestElf, thisElf)
                thisElf = 0
            }
        }
        return highestElf
    }

    fun part2(input: List<String>): Int {
        val elfList = mutableListOf<Int>()
        var thisElf = 0
        input.forEachIndexed { index, value ->
            value.toIntOrNull()?.let {
                thisElf += it
                if (index == input.lastIndex) {
                    elfList.add(thisElf)
                }
            } ?: run {
                elfList.add(thisElf)
                thisElf = 0
            }
        }
        return elfList.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}