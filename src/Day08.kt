fun main() {
    class Instructions(val instructions: String) {
        fun getInstruction(overflowingIndex: Long): Char {
            val index = overflowingIndex % instructions.length.toLong()
            return instructions[index.toInt()]
        }
    }

    class NodeMap(input: List<String>) {
        private val map: Map<String, Pair<String, String>>

        fun getNext(current: String, direction: Char): String {
            return when (direction) {
                'L' -> map[current]?.first!!
                else -> map[current]?.second!!
            }
        }

        fun keys(): Set<String> {
            return map.keys
        }

        init {
            map = input
                .map {
                    "(?<source>[A-Z\\d]+) = \\((?<left>[A-Z\\d]+), (?<right>[A-Z\\d]+)\\)".toRegex().matchEntire(it)
                }
                .groupBy { it!!.groups["source"]!!.value }
                .mapValues { Pair(it.value[0]!!.groups["left"]!!.value, it.value[0]!!.groups["right"]!!.value) }
        }
    }

    fun part1(input: List<String>): Long {
        val instructions = Instructions(input[0])
        val map = NodeMap(input.drop(2))
        var steps = 0L
        var currentNode = "AAA"
        while (currentNode != "ZZZ") {
            currentNode = map.getNext(currentNode, instructions.getInstruction(steps++))
        }
        return steps
    }

    fun part2(input: List<String>): Long {
        val instructions = Instructions(input[0])
        val map = NodeMap(input.drop(2))

        return map.keys().filter { it.endsWith("A") }.map {
            var steps = 0L
            var currentNode = it
            while (!currentNode.endsWith("Z")) {
                currentNode = map.getNext(currentNode, instructions.getInstruction(steps++))
            }
            steps
        }.let { lcm(it) }
    }

    check(part1(readInput("Day08_test_1")) == 2L)
    check(part1(readInput("Day08_test_2")) == 6L)
    check(part2(readInput("Day08_test_3")) == 6L)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}
