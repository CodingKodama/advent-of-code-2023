fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            val firstDigit = "^\\D*(\\d)".toRegex().find(it)?.groupValues?.get(1)
            val secondDigit = "(\\d)\\D*$".toRegex().find(it)?.groupValues?.get(1)
            firstDigit + secondDigit
        }.sumOf { it.toInt() }
    }

    fun wash(input: String): String {
        return input.replace("one|two|three|four|five|six|seven|eight|nine".toRegex()) {
            when (it.value) {
                "one" -> "1"
                "two" -> "2"
                "three" -> "3"
                "four" -> "4"
                "five" -> "5"
                "six" -> "6"
                "seven" -> "7"
                "eight" -> "8"
                "nine" -> "9"
                else -> it.value
            }
        }
    }

    fun rewash(input: String): String {
        return input.reversed().replace("one|two|three|four|five|six|seven|eight|nine".reversed().toRegex()) {
            when (it.value) {
                "one".reversed() -> "1"
                "two".reversed() -> "2"
                "three".reversed() -> "3"
                "four".reversed() -> "4"
                "five".reversed() -> "5"
                "six".reversed() -> "6"
                "seven".reversed() -> "7"
                "eight".reversed() -> "8"
                "nine".reversed() -> "9"
                else -> it.value
            }
        }
    }

    fun part2UglyReverseHack(input: List<String>): Int {
        return input.map {
            val firstDigit = "^\\D*(\\d)".toRegex().find(wash(it))?.groupValues?.get(1)
            val secondDigit = "^\\D*(\\d)".toRegex().find(rewash(it))?.groupValues?.get(1)
            firstDigit + secondDigit
        }.sumOf { it.toInt() }
    }


    check(part1(readInput("Day01_test")) == 142)
    check(part2UglyReverseHack(readInput("Day01_test_part2")) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2UglyReverseHack(input).println()
}
