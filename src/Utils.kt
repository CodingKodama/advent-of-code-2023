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
