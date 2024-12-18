
fun main() {

    var rep = ""

    val inLines = readInput("input18_1")
    //val inLines = readInput("test18_1")

    var nbL = 71 // 71
    var nbC = 71 // 71

    var tabInput = Array(nbL) { IntArray(nbC) { 0 } }

    for (idx in 0 until inLines.size) {
        val (col, lig) = inLines[idx].splitInts()
        if (idx < 1024) {
            tabInput[lig][col] = 1
        }
        else {
            tabInput[lig][col] = 1
            if (possiblePath(nbL, nbC, tabInput) == -1) {
                rep = "$col,$lig"
                break
            }
        }
    }

    println(rep)
}
