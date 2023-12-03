fun main() {
    data class Component(val x: Int, val y: Int, val type: String)

    class ComponentNumber(val x: Int, val y: Int, val number: String) {
        fun isAdjacent(component: Component): Boolean {
            return component.y <= this.y + 1 && component.y >= this.y - 1 && component.x <= this.x + number.length && component.x >= this.x - 1
        }

        override fun toString(): String {
            return "ComponentNumber(x=$x, y=$y, number=$number)"
        }
    }

    fun part1(input: List<String>): Int {
        val components = input.flatMapIndexed { index, it ->
            "[^\\d.]".toRegex().findAll(it).map { Component(it.range.first, index, it.value) }
        }
        val componentNumbers = input.flatMapIndexed { index, it ->
            "\\d+".toRegex().findAll(it).map { ComponentNumber(it.range.first, index, it.value) }
        }

        return componentNumbers.filter { components.any { comp -> it.isAdjacent(comp) } }.sumOf { it.number.toInt() }
    }

    fun part2(input: List<String>): Int {
        val componentNumbers = input.flatMapIndexed { index, it ->
            "\\d+".toRegex().findAll(it).map { ComponentNumber(it.range.first, index, it.value) }
        }
        return input.flatMapIndexed { index, it ->
            "[^\\d.]".toRegex().findAll(it).map { Component(it.range.first, index, it.value) }.filter { "*" == it.type }
        }.sumOf {
            val adjacentNumbers = componentNumbers.filter { number -> number.isAdjacent(it) }
            if (adjacentNumbers.size == 2) adjacentNumbers[0].number.toInt() * adjacentNumbers[1].number.toInt() else 0
        }
    }

    check(part1(readInput("Day03_test")) == 4361)
    check(part2(readInput("Day03_test")) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
