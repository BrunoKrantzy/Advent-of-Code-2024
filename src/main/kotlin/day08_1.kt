
data class Point(val x: Int, val y: Int)

fun main() {

    val inLines = readInput("input08_1")
    //val inLines = readInput("test08_1")

    var rep = 0

    var lstListes = mutableListOf<MutableList<Point>>()
    var lstAnt = mutableListOf<Point>()
    var mapChar = mutableMapOf<Char, Int>()
    var numInMap = -1

    var maxX = inLines[0].length
    var maxY = inLines.size
    var tabNodes = Array (maxY+1) { Array(maxX+1) { '$' } }

    inLines.forEachIndexed { idx, line ->

        for(i in 0 until line.length) {
            tabNodes[idx+1][i+1] = '.' // pour la visu.

            val car = line[i]
            if (car != '.') {
                val p = Point(i+1, idx+1)
                if (mapChar.containsKey(car)) {
                    val idxLstListes = mapChar[car]!!
                    lstListes[idxLstListes].add(p)
                }
                else {
                    numInMap++
                    val l = mutableListOf<Point>()
                    l.add(p)
                    lstListes.add(l)
                    mapChar[car] = numInMap
                }
            }
        }
    }

    lstListes.forEachIndexed { idx, lst ->
        lstAnt = lst.toMutableList()
        for (i in 0 until lstAnt.size - 1) {
            val p1 = lstAnt[i]
            for (j in i+1 until lstAnt.size) {
                val p2 = lstAnt[j]
                val node1x = p1.x - (p2.x - p1.x)
                val node1y = p1.y - (p2.y - p1.y)
                if (node1x in 1..maxX && node1y in 1..maxY) {
                    if (tabNodes[node1y][node1x] != '#') {
                        tabNodes[node1y][node1x] = '#'
                        rep++
                    }
                }

                val node2x = p2.x + (p2.x - p1.x)
                val node2y = p2.y + (p2.y - p1.y)
                if (node2x in 1..maxX && node2y in 1..maxY) {
                    if (tabNodes[node2y][node2x] != '#') {
                        rep++
                        tabNodes[node2y][node2x] = '#'
                    }
                }
            }
        }
    }

    println(rep)
}

