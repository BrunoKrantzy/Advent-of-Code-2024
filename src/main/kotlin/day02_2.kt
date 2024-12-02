import kotlin.math.abs

data class TriRapport (var isOK: Boolean, var liste: MutableList<Int>, var modif: Int)

fun testTri(rapport: TriRapport) : TriRapport {
    val rapLst = rapport.liste

    val listAll = mutableListOf<MutableList<Int>>()
    for (i in 0 until rapLst.size) {
        val lstTry = rapLst.toMutableList()
        lstTry.removeAt(i)
        val lstTryInc = lstTry.sorted()
        val lstTryDec = lstTry.sortedDescending()
        if (lstTryInc == lstTry || lstTryDec == lstTry) {
            listAll.add(lstTry)
        }
    }

    for (it in listAll) {
        val test = testRapport(it, 1)
        if (test.isOK && test.modif < 2) {
            rapport.isOK = true
            rapport.liste = it
            rapport.modif = test.modif
            return rapport
        }
    }

    rapport.isOK = false
    return rapport
}


fun testRapport(liste: MutableList<Int>, mod: Int) : TriRapport {
    var isOk = true
    var retLst = mutableListOf<Int>()
    var modif = mod
    val retRapport = TriRapport(isOk, retLst, modif)

    var v1 = liste[0]
    for (i in 1 until liste.size) {
        val dif = abs(v1 - liste[i])
        if (dif !in 1..3) {
            modif++
            if (i == liste.size - 1) {
                break
            }
        }
        v1 = liste[i]
    }

    if (modif > 1) retRapport.isOK = false
    retRapport.modif = modif
    return retRapport
}


fun main() {

    //val inLines = readInput("input02_1")
    val inLines = readInput("test02_1")

    var rep = inLines.size

    inLines.forEach {
        var lstL = it.splitInts() as MutableList
        var modif = 0
        var rapport = TriRapport(true, lstL, modif)
        rapport = testTri(rapport)

        if (!rapport.isOK) {
            rep--
        }
    }

    println(rep)
}


