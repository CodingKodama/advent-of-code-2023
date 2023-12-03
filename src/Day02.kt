data class Reveal(val red: Int, val green: Int, val blue: Int)
class Game(game: String) {
    val id: Int
    val maxRed: Int
    val maxGreen: Int
    val maxBlue: Int
    private val reveals: List<Reveal>
    val power: Int

    init {
        val find = "Game (\\d+):(.*)".toRegex().find(game)
        id = find?.groupValues?.get(1)?.toInt()!!
        reveals = find.groupValues[2].split(";").map {
            Reveal(
                "(\\d+) red".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?: 0,
                "(\\d+) green".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?: 0,
                "(\\d+) blue".toRegex().find(it)?.groupValues?.get(1)?.toInt() ?: 0
            )
        }

        maxRed = reveals.maxOfOrNull { it.red } ?: 0
        maxGreen = reveals.maxOfOrNull { it.green } ?: 0
        maxBlue = reveals.maxOfOrNull { it.blue } ?: 0
        power = maxRed * maxGreen * maxBlue
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            Game(it)
        }.filter {
            it.maxRed <= 12 && it.maxGreen <= 13 && it.maxBlue <= 14
        }.sumOf { it.id }
    }

    fun part2(input: List<String>): Int {
        return input.map {
            Game(it)
        }.sumOf { it.power }
    }

    check(part1(readInput("Day02_test")) == 8)
    check(part2(readInput("Day02_test")) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
