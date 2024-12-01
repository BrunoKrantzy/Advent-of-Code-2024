
import kotlin.math.abs

fun main() {

    val inLines = readInput("input01_1")
    //val inLines = readInput("test01_1")

    var rep = 0L
    var leftL = mutableListOf<Int>()
    var rightL = mutableListOf<Int>()

    var patLine ="(\\d+)   (\\d+)".toRegex()

    inLines.forEach {
        val line = it
        val matchL = patLine.find(line)
        if (matchL != null) {
            val left = matchL.groups[1]!!.value.toInt()
            val right = matchL.groups[2]!!.value.toInt()
            leftL.add(left)
            rightL.add(right)
        }
    }

    for (i in 0 until inLines.size) {
        val vL = leftL[i]
        val nbV = rightL.count { it == vL }
        rep += vL * nbV
    }

    println(rep)
}
