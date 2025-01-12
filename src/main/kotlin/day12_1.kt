
import java.util.*


fun main() {
    val inLines = readInput("input12_1")
    //val inLines = readInput("test12_1")

    val mapRegionFence = mutableMapOf<Int,Int>()
    val mapRegionCar = mutableMapOf<Int, MutableSet<Point2D>>()
    var numReg = 0
    var repFence = 0

    val nbLignes = inLines.size+2
    val nbCols = inLines.first().length+2
    val tabInput = Array (nbLignes) { Array(nbCols) { '$' } }

    inLines.forEachIndexed { idx, ligne ->
        for (c in 0 until ligne.length) {
            val car = ligne[c]
            tabInput[idx+1][c+1] = car
        }
    }

    val mapPoints = mutableMapOf<Point2D, Char>()
    val lstCells = mutableListOf<Point2D>()

    for (l in 1 until nbLignes-1) {
        for (c in 1 until nbCols-1) {
            val codeCell = tabInput[l][c]
            // recherche de toutes les cellules connexes contenant codeCell
            // on remplit une Queue que l'on épuise au fur et à mesure pour renseigner la map des régions
            var p = Point2D(l, c)
            if (!mapPoints.containsKey(p)) {
                mapPoints[p] = codeCell
                lstCells.add(p)
                val compareByValue: Comparator<Point2D> = compareByDescending { it.x }
                val queue = PriorityQueue<Point2D>(compareByValue)
                queue.add(p)
                while (!queue.isEmpty()) {
                    p = queue.poll()
                    mapPoints[p] = codeCell
                    val setVoisins = p.cardinalNeighbors()
                    setVoisins.forEach {
                        val v = tabInput[it.x][it.y]
                        if (v == codeCell && !mapPoints.containsKey(it)) {
                            lstCells.add(it)
                            queue.add(it)
                            mapPoints[it] = codeCell
                        }
                    }
                }
                numReg++
                mapRegionCar[numReg] = lstCells.toMutableSet()
                lstCells.clear()
            }
        }
    }

    fun calcNBfence(lig:Int, col:Int, car:Char) : Int {
        var fence = 4
        if (tabInput[lig][col-1] == car) fence--
        if (tabInput[lig][col+1] == car) fence--
        if (tabInput[lig-1][col] == car) fence--
        if (tabInput[lig+1][col] == car) fence--

        return fence
    }

    fun majCellule(lig:Int, col:Int, car:Char, region:Int) {
        val nbF = calcNBfence(lig, col, car)
        if (mapRegionFence.containsKey(region))
            mapRegionFence[region] = mapRegionFence[region]!! + nbF
        else
            mapRegionFence[region] = nbF
    }

    // calculer le nb de clôtures par région
    for (elem in mapRegionCar) {
        for (p in elem.value) {
            val car = tabInput[p.x][p.y]
            majCellule(p.x, p.y, car, elem.key)
        }
    }

    for (reg in 1..numReg) {
        var nR = -1
        var nF = -1
        if (mapRegionCar.containsKey(reg))
            nR = mapRegionCar[reg]!!.size
        if (mapRegionFence.containsKey(reg))
            nF = mapRegionFence[reg]!!
        //println("region : $reg = Plots : $nR * Fences : $nF")
        repFence += (nR * nF)
    }

    println("part1 : $repFence")
}

