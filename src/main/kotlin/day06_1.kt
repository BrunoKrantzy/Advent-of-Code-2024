enum class Dir { N, S, W, E }

fun main() {

    val nbL = 132 // 132
    val nbC = 132 // 132

    //val inLines = readInput("test06_1")
    val inLines = readInput("input06_1")
    val tabLab: Array<Array<Char>> = Array (nbL) { Array(nbC) { '$' } }

    var dirGard = Dir.N
    var posGard = Pair(0, 0)

    var setVisited = mutableSetOf<Pair<Int,Int>>()

    inLines.forEachIndexed { nLine, line ->
        line.forEachIndexed { nCol, car ->
            if (car =='^') {
                posGard = Pair(nLine+1, nCol+1)
                setVisited.add(posGard)
            }
            tabLab[nLine+1][nCol+1] = car
        }
    }

    while (true) {
        var carNext = '.'
        when (dirGard) {
            Dir.N -> {
                carNext = tabLab[posGard.first-1][posGard.second]

                if (carNext == '$')
                    break
                if (carNext == '#') {
                    var carTest = tabLab[posGard.first][posGard.second+1]
                    if (carTest != '#' && carTest != '$') {
                        dirGard = Dir.E
                        posGard = Pair(posGard.first, posGard.second+1)
                    }
                    else {
                        dirGard = Dir.S
                        posGard = Pair(posGard.first+1, posGard.second)
                    }
                }
                else { // N
                    posGard = Pair(posGard.first-1, posGard.second)
                }
            }

            Dir.S -> {
                carNext = tabLab[posGard.first+1][posGard.second]

                if (carNext == '$')
                    break
                if (carNext == '#') {
                    var carTest = tabLab[posGard.first][posGard.second-1]
                    if (carTest != '#' && carTest != '$') {
                        dirGard = Dir.W
                        posGard = Pair(posGard.first, posGard.second-1)
                    }
                    else {
                        dirGard = Dir.N
                        posGard = Pair(posGard.first-1, posGard.second)
                    }
                }
                else { // S
                    posGard = Pair(posGard.first+1, posGard.second)
                }
            }

            Dir.E -> {
                carNext = tabLab[posGard.first][posGard.second+1]

                if (carNext == '$')
                    break
                if (carNext == '#') {
                    var carTest = tabLab[posGard.first+1][posGard.second]
                    if (carTest != '#' && carTest != '$') {
                        dirGard = Dir.S
                        posGard = Pair(posGard.first+1, posGard.second)
                    }
                    else {
                        dirGard = Dir.W
                        posGard = Pair(posGard.first, posGard.second-1)
                    }
                }
                else { // E
                    posGard = Pair(posGard.first, posGard.second+1)
                }
            }

            Dir.W -> {
                carNext = tabLab[posGard.first][posGard.second-1]

                if (carNext == '$')
                    break
                if (carNext == '#') {
                    var carTest = tabLab[posGard.first-1][posGard.second]
                    if (carTest != '#' && carTest != '$') {
                        dirGard = Dir.N
                        posGard = Pair(posGard.first-1, posGard.second)
                    }
                    else {
                        dirGard = Dir.E
                        posGard = Pair(posGard.first, posGard.second+1)
                    }
                }
                else { // W
                    posGard = Pair(posGard.first, posGard.second-1)
                }
            }
        }
        setVisited.add(posGard)
    }
    // end while

    println(setVisited.size)
}
