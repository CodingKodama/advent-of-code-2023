fun main() {
    class Hand(handInput: String, joker: Boolean = false) {
        val type: Int
        val bid: Long
        val secondaryValue: Long

        init {
            val handAndBid = handInput.split(" ")
            val handMap = handAndBid[0].groupBy { it }.toMutableMap()
            if (joker) {
                val jokers = handMap['J'] ?: emptyList()
                handMap.filter { it.key != 'J' }.maxByOrNull { it.value.size }
                    ?.let { handMap.put(it.key, it.value + jokers) }
                if (handMap.size != 1) {
                    handMap.remove('J')
                }
            }
            val sortedHand =
                handMap.map { Pair(it.key, it.value.size) }.sortedByDescending { it.second }.toMutableList()


            type = when (sortedHand.size) {
                1 -> 0 // Five of a kind
                2 -> if (sortedHand[0].second == 4) 1 else 2 // Four of a kind or full house
                3 -> if (sortedHand[0].second == 3) 3 else 4 // Three of a kind or two pair
                4 -> 5 // Pair
                else -> 6 // High card
            }
            bid = handAndBid[1].toLong()
            secondaryValue = handAndBid[0]
                .replace("A", "E")
                .replace("K", "D")
                .replace("Q", "C")
                .replace("J", if (joker) "1" else "B")
                .replace("T", "A")
                .toLong(16)
        }
    }

    fun part1(input: List<String>): Long {
        return input.map { Hand(it) }
            .sortedWith(compareByDescending<Hand> { it.type }.thenBy { it.secondaryValue })
            .foldIndexed(0L) { index, winnings, hand -> winnings + (index + 1) * hand.bid }
    }

    fun part2(input: List<String>): Long {
        return input.map { Hand(it, true) }
            .sortedWith(compareByDescending<Hand> { it.type }.thenBy { it.secondaryValue })
            .foldIndexed(0L) { index, winnings, hand -> winnings + (index + 1) * hand.bid }
    }

    check(part1(readInput("Day07_test")) == 6440L)
    check(part2(readInput("Day07_test")) == 5905L)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
