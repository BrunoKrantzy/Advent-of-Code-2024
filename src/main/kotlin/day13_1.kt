
fun main() {

    val inLines = readInput("input13_1")
    //val inLines = readInput("test13_1")

    var bestPrix = Int.MAX_VALUE
    var rep = 0L

    // Prize
    var pX = 0
    var pY = 0
    // A
    var ax = 0
    var ay = 0
    // B
    var bx = 0
    var by = 0

    val patA = "Button A: X\\+(\\d+), Y\\+(\\d+)".toRegex()
    val patB = "Button B: X\\+(\\d+), Y\\+(\\d+)".toRegex()
    val patPrize = "Prize: X=(\\d+), Y=(\\d+)".toRegex()

    var nL = -1
    var test1 = false
    var test2 = false
    var test3 = false

    while (nL < inLines.size) {
        for (i in 1..3) {
            nL++
            val ligne = inLines[nL]
            var matchL = patA.find(ligne)
            if (matchL != null) {
                test1 = true
                ax = matchL.groups[1]!!.value.toInt()
                ay = matchL.groups[2]!!.value.toInt()
            }

            matchL = patB.find(ligne)
            if (matchL != null) {
                test2 = true
                bx = matchL.groups[1]!!.value.toInt()
                by = matchL.groups[2]!!.value.toInt()
            }

            matchL = patPrize.find(ligne)
            if (matchL != null) {
                test3 = true
                pX = matchL.groups[1]!!.value.toInt()
                pY = matchL.groups[2]!!.value.toInt()
            }
        }
        if (!test1 || !test2 || !test3) {
            println("ERREUR")
        }
        nL++ // ligne blanche

        test1 = false
        test2 = false
        test3 = false

        val lstAx = mutableListOf<Int>()
        val lstBx = mutableListOf<Int>()

        var positifX = false
        var i = -1
        while (true) {
            i++
            if (i > 100) break
            val res = (pX - (ax * i)) % bx
            if (res == 0) {
                val v = (pX - (ax * i)) / bx
                if ((v <= 100) && ((ay * i) + (v * by) == pY)) {
                    lstAx.add(i)
                    lstBx.add(v)
                    positifX = true
                }
            }
            if ((ax * i) > pX)
                break
        }

        if (positifX) {
            for (i in 0 until lstAx.size) {
                val ax1 = lstAx[i] * 3
                val bx1 = lstBx[i]
                bestPrix = minOf(bestPrix, ax1 + bx1)
            }

            rep += bestPrix
            bestPrix = Int.MAX_VALUE
        }
    }

    println(rep)
}
