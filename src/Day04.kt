fun main() {
    class Card(game: String) {
        val id: Int
        private val winning: List<Int>
        private val draw: List<Int>

        fun winnings(): Int {
            val matches = matches()
            return if (matches > 0) power(2, matches - 1) else 0
        }

        fun matches(): Int {
            return winning.intersect(draw.toSet()).size
        }

        init {
            val find = "Card +(\\d+):(.*)".toRegex().find(game)
            id = find?.groupValues?.get(1)?.toInt()!!
            val split = find.groupValues[2].split("|")
            winning = split[0].trim().split(" +".toRegex()).map { it.toInt() }
            draw = split[1].trim().split(" +".toRegex()).map { it.toInt() }
        }
    }

    fun calcBonusCards(cardIndex: Int, cards: List<Card>): Int {
        var sum = 0
        if (cardIndex < cards.size) {
            var wins = cards[cardIndex].matches()
            sum += wins
            while (wins > 0) {
                sum += calcBonusCards(cardIndex + wins, cards)
                --wins
            }
        }
        return sum
    }

    fun part1(input: List<String>): Int {
        return input.map { Card(it) }.sumOf { it.winnings() }
    }

    fun part2(input: List<String>): Int {
        val cards = input.map { Card(it) }
        return List(cards.size) { index -> 1 + calcBonusCards(index, cards)}.sum()
    }

    check(part1(readInput("Day04_test")) == 13)
    check(part2(readInput("Day04_test")) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
