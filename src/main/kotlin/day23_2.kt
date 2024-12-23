
fun main() {

    val mapComputers = mutableMapOf<String, MutableList<String>>()

    val inLines = readInput("input23_1")
    //val inLines = readInput("test23_1")

    inLines.forEach { it ->
        val comp1 = it.substring(0..1)
        val comp2 = it.substring(3..4)

        if (mapComputers.containsKey(comp1)) {
            mapComputers[comp1]!!.add(comp2)
        }
        else {
            mapComputers[comp1] = mutableListOf()
            mapComputers[comp1]!!.add(comp2)
        }

        if (mapComputers.containsKey(comp2)) {
            mapComputers[comp2]!!.add(comp1)
        }
        else {
            mapComputers[comp2] = mutableListOf()
            mapComputers[comp2]!!.add(comp1)
        }
    }

    val lstContenus = mutableMapOf<String, MutableList<String>>()
    mapComputers.forEach { comp1 ->
        lstContenus[comp1.key] = mutableListOf()
        comp1.value.forEach { comp2 ->
            mapComputers[comp2]!!.forEach {
                if (it == comp1.key)
                    lstContenus[comp1.key]!!.add(comp2)
            }
        }
    }

    val lstConnex = mutableListOf<String>()

    lstContenus.forEach { key ->
        var lstTemp = mutableListOf(key.key)

        for (i in 0 until key.value.size) {
            val k1 = key.value[i]
            for (j in i+1 until key.value.size) {
                val k2 = key.value[j]
                if (lstContenus[k1]!!.contains(k2) && lstContenus[k2]!!.contains(k1)) {
                    if (!lstTemp.contains(k1))
                        lstTemp.add(k1)
                    if (!lstTemp.contains(k2))
                        lstTemp.add(k2)
                }
            }
        }

        lstTemp.sort()
        val builder = StringBuilder()
        lstTemp.forEach { str ->
            builder.append("$str,")
        }
        val line = builder.dropLast(1).toString()
        lstConnex.add(line)
    }
    lstConnex.sort()

    var bestCount = 0
    var bestLine = ""
    lstConnex.forEach {
        val nbL = lstConnex.filter { str -> str == it }.count()
        if (nbL > bestCount) {
            bestCount = nbL
            bestLine = it
        }
    }

    println(bestLine)
}

