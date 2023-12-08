fun main() {
    class ConversionRange(input: String) {
        val source: Long
        val destination: Long
        val length: Long

        fun contains(value: Long): Boolean {
            return value >= source && value < source + length
        }

        fun convert(value: Long): Long {
            return if (contains(value)) value - source + destination else value
        }

        init {
            input.split(" ").let {
                destination = it[0].toLong()
                source = it[1].toLong()
                length = it[2].toLong()
            }
        }
    }

    class ConversionMap(input: String) {
        val title: String
        private val ranges: List<ConversionRange>

        fun convert(input: Long): Long {
            return ranges.firstOrNull { it.contains(input) }?.convert(input) ?: input
        }

        init {
            val lines = input.split("\r?\n".toRegex())
            title = lines[0].replace(" map:", "")
            ranges = lines.drop(1).map { ConversionRange(it) }
        }
    }

    class SeedMapper(mappingSegments: List<String>) {
        val mappers: List<ConversionMap>

        fun runMapping(seed: Long): Long {
            return mappers.fold(seed) { value, mapper -> mapper.convert(value) }
        }

        init {
            mappers = mappingSegments.map { ConversionMap(it) }
        }
    }

    fun part1(input: String): Long {
        val inputSegments = input.split("\r?\n\r?\n".toRegex())
        val seeds = inputSegments[0].split(" ").let { it.subList(1, it.size) }.map { it.toLong() }
        val seedMapper = SeedMapper(inputSegments.drop(1))
        return seeds.minOfOrNull { seedMapper.runMapping(it) } ?: Long.MAX_VALUE
    }

    fun part2(input: String): Long {
        val inputSegments = input.split("\r?\n\r?\n".toRegex())
        val seeds = inputSegments[0].split(" ").let { it.subList(1, it.size) }.map { it.toLong() }.chunked(2)
        val seedMapper = SeedMapper(inputSegments.drop(1))
        return seeds.minOfOrNull { seedSequence ->
            (seedSequence[0]..<seedSequence[0] + seedSequence[1]).minOfOrNull { seedMapper.runMapping(it) }
                ?: Long.MAX_VALUE
        } ?: Long.MAX_VALUE

    }

    check(part1(readFullInput("Day05_test")) == 35L)
    check(part2(readFullInput("Day05_test")) == 46L)

    val input = readFullInput("Day05")
    part1(input).println()
    part2(input).println()
}
