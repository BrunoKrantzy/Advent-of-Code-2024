
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
        // horizontal
        val lst = it.windowed(4,1)
        rep += lst.filter { w -> w.contains("XMAS") }.count()
        rep += lst.filter { w -> w.contains("SAMX") }.count()

        numChar = 0
        it.forEach { c ->
            tabTxt[numLine][numChar] = c
            numChar++
        }
        numLine++
    }

    // vertical
    var lstVert = mutableListOf<String>()
    for (col in 0 until nbCols) {
        var str = StringBuilder()
        for (line in 0 until nbLines) {
            str.append(tabTxt[line][col])
        }
        lstVert.add(str.toString())
    }
    lstVert.forEach {
        val lst = it.windowed(4,1)
        rep += lst.filter { w -> w.contains("XMAS") }.count()
        rep += lst.filter { w -> w.contains("SAMX") }.count()
    }

    // diagonal 1
    var lstDiag = mutableListOf<String>()
    for (lg in 0 until nbLines-3) {
        for (col in 0 until nbCols-3) {
            var str = StringBuilder()
            for (lenW in 0 .. 3) {
                str.append(tabTxt[lg + lenW][col + lenW])
            }
            lstDiag.add(str.toString())
        }
    }
    lstDiag.forEach {
        val lst = it.windowed(4,1)
        rep += lst.filter { w -> w.contains("XMAS") }.count()
        rep += lst.filter { w -> w.contains("SAMX") }.count()
    }

    // diagonal 2
    var lstDiag2 = mutableListOf<String>()
    for (lg in 0 until nbLines-3) {
        for (col in nbCols-1 downTo 3) {
            var str = StringBuilder()
            for (lenW in 0 .. 3) {
                str.append(tabTxt[lg + lenW][col - lenW])
            }
            lstDiag2.add(str.toString())
        }
    }
    lstDiag2.forEach {
        val lst = it.windowed(4,1)
        rep += lst.filter { w -> w.contains("XMAS") }.count()
        rep += lst.filter { w -> w.contains("SAMX") }.count()
    }

    println(rep)
}

