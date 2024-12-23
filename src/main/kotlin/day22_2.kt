import kotlin.math.floor

fun main() {

    //val inLines = readInput("input22_1")
    val inLines = readInput("test22_1")

    var lstInput = mutableListOf<Long>()

    inLines.forEach { num ->
        lstInput.add(num.toLong())
    }

    fun mix(num1:Long, num2:Long) : Long {
        var result = num1.xor(num2)
        return result
    }

    fun prune(num1:Long) : Long {
        var result = num1 % 16777216
        return result
    }

    var lstListesBuyers = mutableListOf<MutableList<Pair<Int, Int>>>()

    lstInput.forEach { num ->
        var lstBuyer = mutableListOf<Pair<Int, Int>>()
        var lastDigit = num.toString().last().code - 48
        lstBuyer.add(Pair(lastDigit, 0))
        var oldDigit = lastDigit

        var secNum = num
        var result = 0L
        for (i in 1 .. 2000) { // 2000
            result = secNum * 64
            secNum = mix(secNum, result)
            secNum = prune(secNum)

            result = floor(secNum.toDouble() / 32).toLong()
            secNum = mix(secNum, result)
            secNum = prune(secNum)
            result = secNum * 2048

            secNum = mix(secNum, result)
            secNum = prune(secNum)
            result = secNum

            lastDigit = result.toString().last().code - 48
            var prix = lastDigit - oldDigit
            lstBuyer.add(Pair(lastDigit, prix))
            oldDigit = lastDigit
        }
        lstListesBuyers.add(lstBuyer.toMutableList())
    }

    var lstListesSeqBuyers =  mutableListOf<MutableMap<String, Int>>()

    lstListesBuyers.forEach { lstBuyer ->
        var mapSequences = mutableMapOf<String, Int>()

        for (i in 0 until lstBuyer.size - 3) {
            var strSeq = ""
            var prix = 0
            for (j in i..i+3) {
                if (j < lstBuyer.size) {
                    strSeq += (lstBuyer[j].second)
                    strSeq += ","
                    prix = lstBuyer[j].first
                }
            }
            strSeq = strSeq.dropLast(1)

            if (i > 0 && !mapSequences.containsKey(strSeq)) {
                mapSequences[strSeq] = prix
            }
        }

        lstListesSeqBuyers.add(mapSequences.toMutableMap())
    }

    var bestAddition = 0L
    lstListesSeqBuyers.forEachIndexed { idx, liste ->
        liste.forEach { seqPrix ->
            var prix = seqPrix.value
            var addition = prix.toLong()
            lstListesSeqBuyers.forEachIndexed { idx2, liste2 ->
                if (idx2 != idx) {
                    if (liste2.containsKey(seqPrix.key)) {
                        var seqPrix2 = liste2[seqPrix.key]!!
                        addition += seqPrix2
                    }
                }
            }
            bestAddition = maxOf(bestAddition, addition)
        }
    }

    println(bestAddition)
}

