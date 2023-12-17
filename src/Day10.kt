fun main() {
    data class Coord(val x: Int, val y: Int)
    data class PipeNode(val type: Char, var distance: Long = 0L) {

    }

    class PipeMaze(input: List<String>) {
        private val start: Coord
        private val pipeMap: List<List<PipeNode>>
        init {
            var tempStart: Coord = Coord(-1, -1)
            pipeMap = List(input[0].length) { x ->
                List(input.size) { y ->
                    val type = input[y][x]
                    if (type == 'S') {
                        tempStart = Coord(x, y);
                    }
                    PipeNode(type)
                }
            }
            start = tempStart;
        }

        fun calcDistance() {
            var current: Coord
            if (pipeMap[start.x + 1][start.y].type.let { it == '-' || it == 'J' || it == '7' }) {
                current = Coord(start.x + 1, start.y)
            } else if (pipeMap[start.x - 1][start.y].type.let { it == '-' || it == 'L' || it == 'F' }) {
                current = Coord(start.x - 1, start.y)
            } else {
                current = Coord(start.x, start.y + 1)
            }
        }
    }

    fun part1(input: List<String>): Long {
        PipeMaze(input)
        return 0L
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

    check(part1(readInput("Day10_test_1")) == 4L)
    check(part1(readInput("Day10_test_2")) == 8L)

    val input = readInput("Day10")
    part1(input).println()
    part2(input).println()
}
