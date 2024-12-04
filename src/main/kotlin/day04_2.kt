
fun main() {

    val inLines = readInput("input04_1")
    //val inLines = readInput("test04_1")

    var rep = 0
    var nbLines = inLines.size
    var nbCols = inLines[0].length

    val tabTxt: Array<Array<Char>> = Array (nbLines) { Array(nbCols) { '.' } }

    var numLine = 0
    var numChar = 0

    inLines.forEach {
        numChar = 0
        it.forEach { c ->
            tabTxt[numLine][numChar] = c
            numChar++
        }
        numLine++
    }

    val setPat1 = mutableSetOf("MXS", "MMS", "MAS", "MSS")
    val setPat2 = mutableSetOf("SXS", "SMS", "SAS", "SSS")
    val setPat3 = mutableSetOf("MXM", "MMM", "MAM", "MSM")
    val setPat4 = mutableSetOf("SXM", "SMM", "SAM", "SSM")

    for (l in 0 .. inLines.size - 3) {
        val line = inLines[l]
        val lst = line.windowed(3,1)

        for (p in 0 until lst.size) {
            val pat = lst[p]
            if (setPat1.contains(pat)) {
                if (tabTxt[l+1][p+1] == 'A') {
                    if ((tabTxt[l+2][p] == 'M') && (tabTxt[l+2][p+2] == 'S')) {
                        rep++
                    }
                }
            }

            if (setPat2.contains(pat)) {
                if (tabTxt[l+1][p+1] == 'A') {
                    if ((tabTxt[l+2][p] == 'M') && (tabTxt[l+2][p+2] == 'M')) {
                        rep++
                    }
                }
            }

            if (setPat3.contains(pat)) {
                if (tabTxt[l+1][p+1] == 'A') {
                    if ((tabTxt[l+2][p] == 'S') && (tabTxt[l+2][p+2] == 'S')) {
                        rep++
                    }
                }
            }

            if (setPat4.contains(pat)) {
                if (tabTxt[l+1][p+1] == 'A') {
                    if ((tabTxt[l+2][p] == 'S') && (tabTxt[l+2][p+2] == 'M')) {
                        rep++
                    }
                }
            }
        }
    }

    println(rep)
}


