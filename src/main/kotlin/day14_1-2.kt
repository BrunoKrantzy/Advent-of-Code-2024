import kotlin.math.abs

import java.io.File
import java.io.FileWriter

data class Robot(val vH:Int, val vV:Int)


fun main() {

    val fileName = "src/myfile.txt"
    val myfile = File(fileName)
    val writer = FileWriter(myfile)

    var nbL = 103 // 7
    var nbC = 101 // 11
    var nbSeconds = 7000
    var rep = 0

    var tabInput = Array (nbL) { Array(nbC) { mutableListOf<Robot>() } }
    var tabOut = Array (nbL) { Array(nbC) { mutableListOf<Robot>() } }

    val inLines = readInput("input14_1")
    //val inLines = readInput("test14_1")

    fun printTab() {
         for (l in 0 until tabInput.size) {
            for (c in 0 until tabInput[l].size) {
                if (tabInput[l][c].isNotEmpty())
                    print("X")
                else
                    print(" ")
            }
            print("\n")
        }
    }

    fun writeTab(sec:Int) {
        writer.write("$sec\n")
        for (l in 0 until tabInput.size) {
            for (c in 0 until tabInput[l].size) {
                if (tabInput[l][c].isNotEmpty())
                    writer.write("X")
                else
                    writer.write(" ")
            }
            writer.write("\n")
        }
        writer.write("\n\u000c")
    }

    fun transPose(pose:Int, vitesse:Int, size:Int) : Int {
        var pos = pose
        var v = vitesse
        if (v > 0) {
            var res = pos + v
            if (res > size-1) {
                res -= size
            }
            pos = res
        }
        else {
            var res = pose + v
            if (res >= 0) {
                pos = res
            }
            else {
                pos = size - abs(res)
            }
        }
        return pos
    }

    var patRobot = "p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)".toRegex()

    inLines.forEachIndexed { idx, ligne ->
        var matchL = patRobot.find(ligne)
        if (matchL != null) {
            val pC = matchL.groups[1]!!.value.toInt()
            val pL = matchL.groups[2]!!.value.toInt()
            val vH = matchL.groups[3]!!.value.toInt()
            val vV = matchL.groups[4]!!.value.toInt()

            var robot = Robot(vH, vV)
            tabInput[pL][pC].add(robot)
        }
    }

    for (sec in 1 .. nbSeconds) {
        for (l in 0 until tabInput.size) {
            for (c in 0 until tabInput[l].size) {
                var lstCell = tabInput[l][c]
                lstCell.forEach { rob ->
                    val vH = rob.vH
                    val vV = rob.vV
                    val newLine = transPose(l, vV, nbL)
                    val newCol = transPose(c, vH, nbC)
                    tabOut[newLine][newCol].add(rob)
                }
            }
        }

        for (l in 0 until tabInput.size) {
            for (c in 0 until tabInput[l].size) {
                tabInput[l][c].clear()
            }
        }

        for (l in 0 until tabInput.size) {
            for (c in 0 until tabInput[l].size) {
                tabInput[l][c].addAll(tabOut[l][c])
            }
        }

        for (l in 0 until tabOut.size) {
            for (c in 0 until tabOut[l].size) {
                tabOut[l][c].clear()
            }
        }

        // pour la partie 2
        var temoinListes = true
        for (l in 0 until tabInput.size) {
            for (c in 0 until tabInput[l].size) {
                if (tabInput[l][c].size > 1)
                    temoinListes = false
            }
        }
        if (temoinListes) {
            println(sec)
            writeTab(sec)
            printTab()
        }
    }

    writer.close()

    var midL = nbL/2
    var midC = nbC/2

    var nbRobQ1 = 0
    for (l in 0 until midL) {
        for (c in 0 until midC) {
            nbRobQ1 += tabInput[l][c].size
        }
    }

    var nbRobQ2 = 0
    for (l in 0 until midL) {
        for (c in midC+1 until nbC) {
            nbRobQ2 += tabInput[l][c].size
        }
    }

    var nbRobQ3 = 0
    for (l in midL+1 until nbL) {
        for (c in 0 until midC) {
            nbRobQ3 += tabInput[l][c].size
        }
    }

    var nbRobQ4 = 0
    for (l in midL+1 until nbL) {
        for (c in midC+1 until nbC) {
            nbRobQ4 += tabInput[l][c].size
        }
    }

    rep = (nbRobQ1 * nbRobQ2 * nbRobQ3 * nbRobQ4)
    println(rep)
}


