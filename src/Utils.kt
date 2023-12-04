import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()


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
