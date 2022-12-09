import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("dayXX/test")
    val p1Test = part1(testInput)
    println("p1 test = $p1Test")
    check(p1Test == 2)

    val p2Test = part2(testInput)
    println("p2 test = $p2Test")
    check(part2(testInput) == 4)

    val input = readInput("dayXX/real")
    val p1Real = part1(input)
    println("p1 real = $p1Real")

    val p2Real = part2(input)
    println("p2 real = $p2Real")
}