
fun main() {

    val inLines = readInput("input09_1") as MutableList
    //val inLines = readInput("test09_2") as MutableList

    var rep = 0L
    val line = inLines.first()

    val lstFreeSize = mutableListOf<Int>()
    val lstFileSize = mutableListOf<Int>()
    val lstListesMap = mutableListOf<MutableList<Int>>()

    var idFile = -1
    var isFile = true
    for (idx in 0 until line.length) {
        val car = line[idx].code-48
        if (isFile) {
            lstFileSize.add(car)
            idFile++
            lstListesMap.add(mutableListOf())
            repeat (car) {
                lstListesMap[idx].add(idFile)
            }
            isFile = false
        }
        else {
            lstFreeSize.add(car)
            lstListesMap.add(mutableListOf())
            repeat (car) {
                lstListesMap[idx].add(-1)
            }
            isFile = true
        }
    }

    val posFile = lstFileSize.size-1
    var depDone = true
    while (depDone) {
        depDone = false
        for (file in posFile downTo 1) {
            val sFile = lstFileSize[file]
            if (sFile > 0) {
                for (free in 0 until file) {
                    val sFree = lstFreeSize[free]
                    if (sFile <= sFree) {
                        val ixListeFree = 1 + (free * 2)
                        val ixListeFile = (file * 2)
                        val lstTemp = mutableListOf<Int>()
                        for (element in lstListesMap[ixListeFree]) {
                            if (element != -1) {
                                lstTemp.add(element)
                            }
                        }
                        for (i in 1..sFile) {
                            lstTemp.add(file)
                        }
                        if (sFree - sFile != 0) {
                            for (j in 1..(sFree - sFile)) {
                                lstTemp.add(-1)
                            }
                        }
                        // maj lstListeMap
                        lstListesMap[ixListeFree].clear()
                        lstListesMap[ixListeFree].addAll(lstTemp)

                        lstTemp.clear()
                        for (i in 1..sFile) {
                            lstTemp.add(-1)
                        }
                        lstListesMap[ixListeFile].clear()
                        lstListesMap[ixListeFile].addAll(lstTemp)

                        // maj lstFree
                        lstFreeSize[free] = sFree - sFile
                        // maj lstFile
                        lstFileSize[file] = 0

                        depDone = true
                        break
                    }
                }
            }
        }
    }

    val lstFinale = mutableListOf<Int>()
    lstListesMap.forEach {
        it.forEach { item ->
            lstFinale.add(item)
        }
    }

    lstFinale.forEachIndexed { idx, it ->
        if (it > -1)
            rep += it * idx
    }

    println(rep)
}
