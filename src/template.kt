
fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/test")
    check(part1(testInput) == 24000)

    val input = readInput("day01/real")
    println(part1(input))
    println(part2(input))
}