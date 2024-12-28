
fun main() {

    val inLines = readInput("input25_1")
    //val inLines = readInput("test25_1")

    var rep = 0
    var tabIn = Array(7) { Array(5) { '*' } }
    var lstLocks = mutableListOf<IntArray>()
    var lstKeys = mutableListOf<IntArray>()
    var typeBloc = ""
    var cpL = -1

    inLines.forEachIndexed { idx, line ->
        if (line.isNotEmpty()) {
            cpL++
            if (cpL == 0) {
                if (line.contains('#'))
                    typeBloc = "Lock"
                else
                    typeBloc = "Key"
            }
            line.forEachIndexed { ix, car ->
                tabIn[cpL][ix] = car
            }
            if (cpL == 6) {
                var tabD = Array(5) { 0 }
                for (c in 0..4) {
                    var pin = 0
                    for (l in 1..5) {
                        if (tabIn[l][c] == '#')
                            pin++
                    }
                    tabD[c] = pin
                }
                if (typeBloc == "Lock")
                    lstLocks.add(tabD.toIntArray())
                else
                    lstKeys.add(tabD.toIntArray())
            }
        }
        else {
            cpL = -1
        }
    }

    lstLocks.forEach { lock ->
        lstKeys.forEach { key ->
            var test = true
            for (pin in 0..4) {
                if (key[pin] + lock[pin] > 5) {
                    test = false
                    break
                }
            }
            if (test)
                rep++
        }
    }

    println(rep)
}

