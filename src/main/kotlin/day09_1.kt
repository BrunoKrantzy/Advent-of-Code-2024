
fun main() {

    val inLines = readInput("input09_1") as MutableList
    //val inLines = readInput("test09_1") as MutableList

    var rep = 0L
    val line = inLines.first()

    var lstMap = mutableListOf<Long>()
    val stack = MutableStack<Long>()

    var idFile = -1L
    var isFile = true
    for (idx in 0 until line.length) {
        val car = line[idx].code-48
        if (isFile) {
            idFile++
            repeat (car) {
                lstMap.add(idFile)
                stack.push(idFile)
            }
            isFile = false
        }
        else {
            repeat (car) {
                lstMap.add(-1)
            }
            isFile = true
        }
    }

    var nbPoints = 0
    lstMap.forEachIndexed { idx, v ->
        if (v == -1L) {
            nbPoints++
            val n = stack.pop()
            lstMap[idx] = n
        }
    }
    lstMap = lstMap.dropLast(nbPoints) as MutableList

    lstMap.forEachIndexed { idx, it ->
        rep += ((it.toInt()) * idx)
    }

    println(rep)
}

