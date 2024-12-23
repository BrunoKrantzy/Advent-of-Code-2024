import kotlin.math.floor

fun main() {

    val inLines = readInput("input22_1")
    //val inLines = readInput("test22_1")

    var rep = 0L
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

    lstInput.forEach { num ->
        var secNum = num
        var result = 0L
        for (i in 1 .. 2000) {
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
        }
        rep += result
    }

    println(rep)
}

