import java.math.BigInteger

fun main() {

    val inLines = readInput("input24_1")
    //val inLines = readInput("test24_1")

    data class Ope(var w1:String, var op:String, var w2:String, var result:String)

    var mapWires = mutableMapOf<String, Int>()
    var lstOpe = mutableListOf<Ope>()

    val patLine = "(\\S{3}) (AND|XOR|OR) (\\S{3}) -> (\\S{3})".toRegex()
    var suite = false

    inLines.forEach { line ->
        if (line.isEmpty())
            suite = true

        if (!suite) {
            val wire = line.substring(0..2)
            val prod = line.last().code-48
            mapWires[wire] = prod
        }
        else {
            var matchL = patLine.find(line)
            if (matchL != null) {
                val wire1 = matchL.groups[1]!!.value
                val ope = matchL.groups[2]!!.value
                val wire2 = matchL.groups[3]!!.value
                val result = matchL.groups[4]!!.value

                val cablage = Ope(wire1, ope, wire2, result)
                lstOpe.add(cablage)
            }
        }
    }

    fun calcOpe(w1:Int, ope:String, w2:Int) : Int {
        var res = -1
        when (ope) {
            "AND" -> {
                if (w1 == 1 && w2 == 1)
                    res = 1
                else
                    res = 0
            }
            "OR" -> {
                if (w1 == 1 || w2 == 1)
                    res = 1
                else
                    res = 0
            }
            "XOR" -> {
                if (w1 != w2)
                    res = 1
                else
                    res = 0
            }
        }
        return res
    }

    while (true) {
        var testComplet = true
        lstOpe.forEach { cab ->
            if (mapWires.containsKey(cab.w1) && mapWires.containsKey(cab.w2)) {
                val resCab = calcOpe(mapWires[cab.w1]!!, cab.op, mapWires[cab.w2]!!)
                mapWires[cab.result] = resCab
            }
            else {
                testComplet = false
            }
        }

        if (testComplet)
            break
    }

    val mapSorted = mapWires.toSortedMap()
    val binStr = StringBuilder()

    mapSorted.forEach {
        if (it.key.first() == 'z') {
            binStr.append(it.value)
        }
    }

    val binResult = binStr.reverse().toString()
    val rep = BigInteger(binResult, 2).toLong()

    println(rep)
}

