
fun rule1 (num:Long) : MutableList<Long> {
    var newLst = mutableListOf<Long>()
    var str = num.toString()
    var left = str.substring(0, str.length/2)
    var right = str.substring(str.length/2, str.length)

    var st = right.dropWhile { str.first() == '0' }
    if (st.isEmpty()) st = "0"

    newLst.add(left.toLong())
    newLst.add(st.toLong())
    return newLst
}


fun main() {

    //val inLines = readInput("input11_1")
    val inLines = readInput("test11_1")

    val blink = 25

    var rep = 0L

    var lstStones = mutableListOf<MutableList<Long>>()
    var stones = inLines.first().splitLongs()

    stones.forEach {
        var lst = mutableListOf(it)
        lstStones.add(lst)
    }

    for (b in 1 .. blink) {
        for (allStones in lstStones) {
            var newListe = mutableListOf<Long>()
            for (st in 0 until allStones.size) {
                var stone = allStones[st]
                if (stone == 0L) {
                    newListe.add(1L)
                }
                else if ((stone.toString().length) % 2 == 0) {
                    var nLst = rule1(stone)
                    newListe.addAll(nLst)
                }
                else {
                    newListe.add(stone * 2024)
                }
            }
            allStones.clear()
            allStones.addAll(newListe)
            newListe.clear()
        }
    }


    lstStones.forEach {
        rep += it.size
    }

    println(rep)
}

