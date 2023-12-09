fun main() {
    class SensoryData(data: String) {
        private val initialValues: List<Long> = data.split(" ").map { it.toLong() }

        private fun predict(values: List<Long>, predictionData: List<Long>): List<Long> {
            return if (values.isEmpty() || values.all { it == 0L }) {
                predictionData
            } else {
                predict(values.zipWithNext().map { it.second - it.first }, predictionData + values.last())
            }
        }
        fun predict(): Long {
            return predict(initialValues, emptyList()).sum()
        }

        fun predictBackwards(): Long {
            return predict(initialValues.reversed(), emptyList()).sum()
        }
    }

    fun part1(input: List<String>): Long {
        return input.map { SensoryData(it) }.sumOf { it.predict() }
    }

    fun part2(input: List<String>): Long {
        return input.map { SensoryData(it) }.sumOf { it.predictBackwards() }
    }

    check(part1(readInput("Day09_test")) == 114L)
    check(part2(readInput("Day09_test")) == 2L)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}
