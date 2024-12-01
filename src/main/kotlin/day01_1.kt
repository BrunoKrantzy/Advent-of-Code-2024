import kotlin.math.abs

fun main() {

    val inLines = readInput("input01_1")
    //val inLines = readInput("test01_1")

    var rep = 0L
    val leftL = mutableListOf<Int>()
    val rightL = mutableListOf<Int>()

    val patLine ="(\\d+) {3}(\\d+)".toRegex()

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

    leftL.sort()
    rightL.sort()

    for (i in inLines.indices) {
        rep += abs(leftL[i] - rightL[i])
    }

    println(rep)
}

