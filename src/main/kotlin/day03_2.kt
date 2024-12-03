
fun main() {

    val inLines = readInput("input03_1")
    //val inLines = readInput("test03_1")

    var rep = 0L
    var doCount = true
    val patLine ="(mul\\((\\d+),(\\d+)\\))|(don't\\(\\))|(do\\(\\))".toRegex()

    inLines.forEach {
        val line = it
        var matchL = patLine.find(line)
        while (matchL != null) {
            val grp = matchL.groupValues
            when (grp[0]) {
                "do()" -> doCount = true
                "don't()" -> doCount = false
                else -> {
                    if (doCount) {
                        val v1 = grp[2].toInt()
                        val v2 = grp[3].toInt()
                        rep += v1 * v2
                    }
                }
            }
            matchL = matchL.next()
        }
    }
    println(rep)
}


