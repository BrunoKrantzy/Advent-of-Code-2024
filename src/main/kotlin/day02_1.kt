import kotlin.math.abs

fun main() {

    val inLines = readInput("input02_1")
    //val inLines = readInput("test02_1")

    var rep = inLines.size

    inLines.forEach {
        var lstL = it.splitInts()
        val lstLInc = lstL.sorted()
        val lstDec = lstL.sortedDescending()

        if (lstLInc == lstL || lstDec == lstL) {
            var v1 = lstL[0]
            var ok = true
            for (i in 1 until lstL.size) {
                if (abs(v1 - lstL[i]) !in 1..3) {
                    ok = false
                }
                v1 = lstL[i]
            }
            if (!ok) rep--
        }
        else
            rep--
    }

    println(rep)
}


