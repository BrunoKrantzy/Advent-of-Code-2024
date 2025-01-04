
fun main() {

    val inLines = readInput("input10_1")
    //val inLines = readInput("test10_2")

    val nbLignes = inLines.size
    val nbCols = inLines.first().length

    val tabTrail = Array (nbLignes + 2) { Array(nbCols + 2) { -9 } }
    val lstZeros = mutableSetOf<Int>()

    var tabCells = Array (2 + ((nbLignes) * (nbCols))) { -1 }

    var rep = 0
    var cible = 9

    var cPos = 0
    inLines.forEachIndexed { idx, ligne ->
        ligne.forEachIndexed { idxCol, v ->
            cPos++
            tabTrail[idx+1][idxCol+1] = v.code - 48
            if (v.code-48 == 0) {
                lstZeros.add(cPos)
            }
            tabCells[cPos] = v.code - 48
        }
    }

    data class Element(var num:Int, var dep:Int)

    fun trouveSuite(element:Element) : MutableList<Element> {
        var lstRetour = mutableListOf<Element>()
        var dep = element.dep
        var num = element.num+1

        if (dep > nbCols) {
            if (tabCells[dep - nbCols] == num) {
                var el = Element(num, dep-nbCols)
                lstRetour.add(el)
            }
        }

        if (dep % nbCols != 1) {
            if (tabCells[dep - 1] == num) {
                var el = Element(num, dep-1)
                lstRetour.add(el)
            }
        }

        if (dep % nbCols != 0) {
            if (tabCells[dep + 1] == num) {
                var el = Element(num, dep+1)
                lstRetour.add(el)
            }
        }

        if (dep < tabCells.size - nbCols) {
            if (tabCells[dep + nbCols] == num) {
                var el = Element(num, dep+nbCols)
                lstRetour.add(el)
            }
        }

        return lstRetour
    }

    lstZeros.forEach { it ->
        val listeElements = mutableListOf<Element>()
        val elem0 = Element(0, it)

        val listeSuite = trouveSuite(elem0)
        listeElements.addAll(listeSuite)

        for (i in 1 until cible) {
            val lst = mutableListOf<Element>()
            for (j in listeSuite) {
                lst.addAll(trouveSuite(j))
            }
            listeElements.addAll(lst)
            listeSuite.clear()
            listeSuite.addAll(lst)
        }

        val nb = listeElements.filter { it.num == cible }.count()
        rep += nb
        listeElements.clear()
    }

    println("rep : $rep")
}

