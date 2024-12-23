
fun main() {

    var rep = 0
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
            mapComputers[comp1] = mutableListOf<String>()
            mapComputers[comp1]!!.add(comp2)
        }

        if (mapComputers.containsKey(comp2)) {
            mapComputers[comp2]!!.add(comp1)
        }
        else {
            mapComputers[comp2] = mutableListOf<String>()
            mapComputers[comp2]!!.add(comp1)
        }
    }

    val lstContenus = mutableMapOf<String, MutableList<String>>()
    mapComputers.forEach { comp1 ->
        lstContenus[comp1.key] = mutableListOf<String>()
        comp1.value.forEach { comp2 ->
            mapComputers[comp2]!!.forEach { it ->
                if (it == comp1.key)
                    lstContenus[comp1.key]!!.add(comp2)
            }
        }
    }

    val setConnex = mutableSetOf<MutableList<String>>()
    lstContenus.forEach { key ->
        for (i in 0 until key.value.size) {
            val k1 = key.value[i]
            for (j in i+1 until key.value.size) {
                val k2 = key.value[j]
                if (lstContenus[k1]!!.contains(k2) && lstContenus[k2]!!.contains(k1)) {
                    val lstTemp = mutableListOf(key.key, k1, k2)
                    lstTemp.sort()
                    if (!setConnex.contains(lstTemp)) {
                        for (k in 0..2) {
                            val c = lstTemp[k]
                            if (c.first() == 't') {
                                rep++
                                break
                            }
                        }
                        setConnex.add(lstTemp)
                    }
                }
            }
        }
    }

    println(rep)
}

