
fun main() {
    var rep = 0

    val inLines = readInput("input18_1")
    //val inLines = readInput("test18_1")

    var nbL = 71 // 71
    var nbC = 71 // 71
    var tabInput = Array(nbL) { IntArray(nbC) { 0 } }

    inLines.forEachIndexed { idx, ligne ->
        val (col, lig) = ligne.splitInts()
        if (idx < 1024) {
            tabInput[lig][col] = 1
        }
    }
    rep = possiblePath(nbL, nbC, tabInput)

    println(rep)
}


