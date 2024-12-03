
fun main() {

    val inLines = readInput("input03_1")
    //val inLines = readInput("test03_1")

    var rep = 0L
    val patLine ="mul\\((\\d+),(\\d+)\\)".toRegex()

    inLines.forEach {
        val line = it
        var matchL = patLine.find(line)
        while (matchL != null) {
            val left = matchL.groups[1]!!.value.toInt()
            val right = matchL.groups[2]!!.value.toInt()
            rep += left * right
            matchL = matchL.next()
        }
    }

    println(rep)
}

