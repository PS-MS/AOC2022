package day07

import readInput

fun main() {
    open class DeviceObj {

    }

    data class DeviceFile(val fileSize: Long, val name: String) : DeviceObj()
    data class DeviceDir(val name: String, val content: List<DeviceObj>) : DeviceObj()

    fun buildDirectoryTree(input: List<String>): HashMap<String, MutableList<DeviceFile>> {
        val deviceContent: HashMap<String, MutableList<DeviceFile>> =
            hashMapOf(Pair("/", mutableListOf()))
        var currentPath = ""
        input.forEach {
            val parts = it.split(" ")
            when (parts[0]) {
                "$" -> {
                    when (parts[1]) {
                        "cd" -> {
                            when (parts[2]) {
                                ".." -> currentPath = currentPath.substringBeforeLast("/")
                                "/" -> currentPath = "/"
                                else -> currentPath += "/${parts[2]}"
                            }
                        }
                    }
                }
                "dir" -> {
                    val dirPath = "$currentPath/${parts[1]}"
                    if (!deviceContent.contains(dirPath)) {
                        deviceContent[dirPath] = mutableListOf()
                    }
                }
                "ls" -> {} // the next parts will be for this directory
                else -> {
                    val file = DeviceFile(parts[0].toLong(), parts[1])
                    var recursivePath = currentPath
                    while (recursivePath.isNotBlank()) {
                        deviceContent[recursivePath]?.add(file)
                        recursivePath = recursivePath.substringBeforeLast("/")
                    }
                }
            }
        }
        return deviceContent
    }

    fun part1(input: List<String>): Long {
        val deviceContent = buildDirectoryTree(input)
        val filtered =
            deviceContent.asIterable().filter { it.value.sumOf { it.fileSize } <= 100000 }
        return filtered.sumOf { it.value.sumOf { it.fileSize } }
    }

    fun part2(input: List<String>): Long {
        val dirMap = buildDirectoryTree(input)
        val totalSpace = 70000000
        val updateSpace = 30000000
        val usedSpace = dirMap["/"]?.sumOf { it.fileSize } ?: 0
        val unusedSpace = totalSpace - usedSpace
        val requiredSpace = updateSpace - unusedSpace
        val dirCandidates =
            dirMap.asIterable().filter { it.value.sumOf { it.fileSize } >= requiredSpace }
        return dirCandidates.minOf { it.value.sumOf { it.fileSize } }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day07/test")
//    val p1Test = part1(testInput)
//    println("p1 test = $p1Test")
//    check(p1Test == 95437L)

    val p2Test = part2(testInput)
    println("p2 test = $p2Test")
    check(part2(testInput) == 24933642L)

    val input = readInput("day07/real")
    val p1Real = part1(input)
    println("p1 real = $p1Real")

    val p2Real = part2(input)
    println("p2 real = $p2Real")
}