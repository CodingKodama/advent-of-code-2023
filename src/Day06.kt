import kotlin.math.*

fun main() {
    class RaceRecord(val time: Long, val distance: Long) {
        fun getWinningInterval(): Pair<Long, Long>? {
            return getRealRoots(1, -time, distance + 1)?.let {
                Pair(
                    ceil(max(0.0, it.first)).toLong(), floor(min(it.second, time.toDouble())).toLong()
                )
            }
        }

        fun getNumberOfWinningSolutions(): Long {
            return getWinningInterval().let { it!!.second - it.first + 1 }
        }
    }

    fun part1(input: List<String>): Long {
        return input[0].split("\\s+".toRegex()).zip(input[1].split("\\s+".toRegex())).asSequence().drop(1)
            .map { RaceRecord(it.first.toLong(), it.second.toLong()) }.map { it.getNumberOfWinningSolutions() }
            .reduce { acc: Long, i: Long -> acc * i }
    }

    fun getNumber(poorlyFormattedNumber: String): Long {
        return poorlyFormattedNumber.split(":")[1].replace("\\s".toRegex(), "").toLong()
    }

    fun part2(input: List<String>): Long {
        return RaceRecord(getNumber(input[0]), getNumber(input[1])).getNumberOfWinningSolutions()
    }

    check(part1(readInput("Day06_test")) == 288L)
    check(part2(readInput("Day06_test")) == 71503L)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
