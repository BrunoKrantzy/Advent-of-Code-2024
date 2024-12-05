
fun main() {

    val inLines = readInput("input05_1")
    //val inLines = readInput("test05_2")

    var rep = 0
    var repSuite = 0

    val lstRules = mutableListOf<Pair<Int,Int>>()
    val lstUpdates = mutableListOf<List<Int>>()
    val lstBadUpdates = mutableListOf<List<Int>>()

    inLines.forEach { line ->
        if (line.contains('|')) {
            val l = line.split('|').map { it.toInt() }
            val x = l.first()
            val y = l.last()
            lstRules.add(Pair(x,y))
        }
        else if (line.isNotEmpty()) {
            val l = line.split(',').map { it.toInt() }
            lstUpdates.add(l)
        }
    }

    // partie 1
    lstUpdates.forEach { update ->
        var good = true
        val idxMid: Int = update.size / 2
        update.forEachIndexed { idx, page ->
            val posPage = idx
            val lstRulesPage = lstRules.filter { it.first == page }
            if (lstRulesPage.isNotEmpty()) {
                lstRulesPage.forEach { r ->
                    val y = r.second
                    if (update.contains(y)) {
                        val idxY = update.indexOf(y)
                        if (idxY < posPage)
                            good = false
                    }
                }
            }
        }
        if (good) {
            rep += update[idxMid]
        }
        else {
            lstBadUpdates.add(update)
        }
    }

    // partie 2
    lstBadUpdates.forEach { update ->
        val idxMid: Int = update.size / 2
        val newUpdate = update.toMutableList()
        update.forEach { page ->
            var lstRulesPage = lstRules.filter { it.first == page }
            if (lstRulesPage.isNotEmpty()) {
                lstRulesPage.forEach { r ->
                    val y = r.second
                    if (newUpdate.contains(y)) {
                        val idxY = newUpdate.indexOf(y)
                        val idxPage = newUpdate.indexOf(page)
                        if (idxY < idxPage) {
                            newUpdate.add(idxY, page)
                            newUpdate.removeAt(idxPage+1)
                        }
                    }
                }
            }
        }
        repSuite += newUpdate[idxMid]
    }

    println("rep : $rep")
    println("repSuite : $repSuite")
}

