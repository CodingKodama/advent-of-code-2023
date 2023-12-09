import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.math.sqrt

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Reads lines from the given input txt file.
 */
fun readFullInput(name: String) = Path("src/$name.txt").readText()


/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun power(baseVal: Int, exponentVal: Int): Int {
    var exponent = exponentVal
    var result = 1

    while (exponent != 0) {
        result *= baseVal
        --exponent
    }
    return result
}

fun discriminant(a: Long, b: Long, c: Long): Long {
    return b * b - 4 * a * c
}

fun getRealRoots(a: Long, b: Long, c: Long): Pair<Double, Double>? {
    val discriminant = discriminant(a, b, c)
    if (discriminant < 0) {
        return null
    }
    return Pair(
        (-b - sqrt(discriminant.toDouble())) / (2 * a),
        (-b + sqrt(discriminant.toDouble())) / (2 * a)
    )
}

fun lcm(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}

fun lcm(numbers: List<Long>): Long {
    return numbers.reduce { lcm, value -> lcm(lcm, value) }
}