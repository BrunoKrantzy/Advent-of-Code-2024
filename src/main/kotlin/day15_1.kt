
enum class DirRobot { U, D, L, R }

fun main() {

    var nbL = 50 // 10
    var nbC = 50 // 10
    var rep = 0
    var posRobot = Pair(0, 0)

    var tabInput = Array (nbL) { Array(nbC) { '.' } }
    var lstMoves = mutableListOf<Char>()

    val inLines = readInput("input15_1")
    //val inLines = readInput("test15_1")


    fun printTab() {
        for (l in 0 until tabInput.size) {
            for (c in 0 until tabInput[l].size) {
                print(tabInput[l][c])
            }
            print("\n")
        }
    }

    inLines.forEachIndexed { idx, ligne ->
        if (idx < nbL) {
            ligne.forEachIndexed { pos, c ->
                var car = c
                if (car == '@')
                    posRobot = Pair(idx, pos)
                if (car == '.')
                    car = '.'
                tabInput[idx][pos] = car
            }
        }
        else {
            ligne.forEach {
                lstMoves.add(it)
            }
        }
    }

    fun actionRobotRight() {
        var posActLig = posRobot.first
        var posActCol = posRobot.second
        var nextCell = tabInput[posActLig][posActCol+1]

        if (nextCell == '.') {
            tabInput[posActLig][posActCol] = '.' // effacer robot
            tabInput[posActLig][posActCol+1] = '@' // replacer robot
            posRobot = Pair(posActLig, posActCol+1)
        }
        else if (nextCell == '#')
            posRobot = Pair(posActLig, posActCol) // rien ne bouge
        else { // une box O
            for (p in posActCol+1 until nbC) {
                val car = tabInput[posActLig][p]
                if (car == '.') {
                    tabInput[posActLig][posActCol] = '.' // effacer robot
                    tabInput[posActLig][posActCol+1] = '@' // replacer robot
                    posRobot = Pair(posActLig, posActCol+1)
                    tabInput[posActLig][p] = 'O' // replacer le O
                    break
                }
                else if (car == '#') {
                    break // rien ne bouge
                }
            }
        }
    }

    fun actionRobotLeft() {
        var posActLig = posRobot.first
        var posActCol = posRobot.second
        var nextCell = tabInput[posActLig][posActCol-1]

        if (nextCell == '.') {
            tabInput[posActLig][posActCol] = '.' // effacer robot
            tabInput[posActLig][posActCol-1] = '@' // replacer robot
            posRobot = Pair(posActLig, posActCol-1)
        }
        else if (nextCell == '#')
            posRobot = Pair(posActLig, posActCol) // rien ne bouge
        else { // une box O
            for (p in posActCol-1 downTo 0) {
                val car = tabInput[posRobot.first][p]
                if (car == '.') {
                    tabInput[posActLig][posActCol] = '.' // effacer robot
                    tabInput[posActLig][posActCol-1] = '@' // replacer robot
                    posRobot = Pair(posActLig, posActCol-1)
                    tabInput[posActLig][p] = 'O' // replacer le O
                    break
                }
                else if (car == '#') {
                    break // rien ne bouge
                }
            }
        }
    }

    fun actionRobotUp() {
        var posActLig = posRobot.first
        var posActCol = posRobot.second
        var nextCell = tabInput[posActLig-1][posActCol]

        if (nextCell == '.') {
            tabInput[posActLig][posActCol] = '.' // effacer robot
            tabInput[posActLig-1][posActCol] = '@' // replacer robot
            posRobot = Pair(posActLig-1, posActCol)
        }
        else if (nextCell == '#')
            posRobot = Pair(posActLig, posActCol) // rien ne bouge
        else { // une box O
            for (p in posRobot.first-1 downTo 0) {
                val car = tabInput[p][posActCol]
                if (car == '.') {
                    tabInput[posActLig][posActCol] = '.' // effacer robot
                    tabInput[posActLig-1][posActCol] = '@' // replacer robot
                    posRobot = Pair(posActLig-1, posActCol)
                    tabInput[p][posActCol] = 'O' // replacer le O
                    break
                }
                else if (car == '#') {
                    break // rien ne bouge
                }
            }
        }
    }

    fun actionRobotDown() {
        var posActLig = posRobot.first
        var posActCol = posRobot.second
        var nextCell = tabInput[posActLig+1][posActCol]

        if (nextCell == '.') {
            tabInput[posActLig][posActCol] = '.' // effacer robot
            tabInput[posActLig+1][posActCol] = '@' // replacer robot
            posRobot = Pair(posActLig+1, posActCol)
        }
        else if (nextCell == '#')
            posRobot = Pair(posActLig, posActCol) // rien ne bouge
        else { // une box O
            for (p in posRobot.first+1 until nbL) {
                val car = tabInput[p][posActCol]
                if (car == '.') {
                    tabInput[posActLig][posActCol] = '.' // effacer robot
                    tabInput[posActLig+1][posActCol] = '@' // replacer robot
                    posRobot = Pair(posActLig+1, posActCol)
                    tabInput[p][posActCol] = 'O' // replacer le O
                    break
                }
                else if (car == '#') {
                    break // rien ne bouge
                }
            }
        }
    }

    lstMoves.forEach { dir ->
        when (dir) {
            '>' -> {
                actionRobotRight()
            }
            '<' -> {
                actionRobotLeft()
            }
            '^' -> {
                actionRobotUp()
            }
            'v' -> {
                actionRobotDown()
            }
        }
    }

    for (l in 0 until tabInput.size) {
        for (c in 0 until tabInput[l].size) {
            val car = tabInput[l][c]
            if (car == 'O') {
                val dist = (100 * l) + c
                rep += dist
            }
        }
    }

    printTab()
    println(rep)
}

