import kotlin.math.pow

fun main() {

    val inLines = readInput("input17_1")
    //val inLines = readInput("test17_1")

    var lstInstruc = mutableListOf<Int>()

    var regA = 0L
    var regB = 0L
    var regC = 0L
    var pointeur = 0

    var patRegistre = "Register ([ABC]): (\\d+)".toRegex()

    inLines.forEach { ligne ->
        var matchL = patRegistre.find(ligne)
        if (matchL != null) {
            val reg = matchL.groups[1]!!.value.toString()
            val vReg = matchL.groups[2]!!.value.toString()
            when (reg) {
                "A" -> regA = vReg.toLong()
                "B" -> regB = vReg.toLong()
                "C" -> regC = vReg.toLong()
            }
        }
        else if (ligne.isNotEmpty()) {
            lstInstruc = ligne.splitInts() as MutableList
        }
    }

    fun  trtOperand(op:Int) : Long {
        var result = 0L
        if (op == 4)
            result = regA
        else if (op == 5)
            result = regB
        else if (op == 6)
            result = regC
        else
            result = op.toLong()

        return result
    }

    var opCode = ""
    var operand = 0
    var valOperand = 0L
    val lstOutputs = mutableListOf<Long>()

    while (true) {
        var instruc = 0
        if (pointeur < lstInstruc.size-1)
            instruc = lstInstruc[pointeur]
        else
            break

        when (instruc) {
            0 -> {
                opCode = "adv"
                pointeur++
                operand = lstInstruc[pointeur]
                valOperand = trtOperand(operand)
                val result = (regA / ((2.0).pow(valOperand.toInt())).toLong())
                regA = result
                pointeur++
            }
            1 -> {
                opCode = "bxl"
                pointeur++
                operand = lstInstruc[pointeur]
                valOperand = operand.toLong()
                val result = (regB.xor(valOperand))
                regB = result
                pointeur++
            }
            2 -> {
                opCode = "bst"
                pointeur++
                operand = lstInstruc[pointeur]
                valOperand = trtOperand(operand)
                val result = (valOperand % 8)
                regB = result
                pointeur++
            }
            3 -> {
                opCode = "jnz"
                pointeur++
                operand = lstInstruc[pointeur]
                if (regA != 0L) {
                    valOperand = operand.toLong()
                    pointeur = valOperand.toInt()
                }
                else
                    pointeur++
            }
            4 -> {
                opCode = "bxc"
                pointeur++
                var result = regB.xor(regC)
                regB = result
                pointeur++
            }
            5 -> {
                opCode = "out"
                pointeur++
                operand = lstInstruc[pointeur]
                valOperand = trtOperand(operand)
                val result = (valOperand % 8)
                lstOutputs.add(result)
                pointeur++
            }
            6 -> {
                opCode = "bdv"
                pointeur++
                operand = lstInstruc[pointeur]
                valOperand = trtOperand(operand)
                val result = (regA / ((2.0).pow(valOperand.toInt())).toLong())
                regB = result
                pointeur++
            }
            7 -> {
                opCode = "cdv"
                pointeur++
                operand = lstInstruc[pointeur]
                valOperand = trtOperand(operand)
                val result = (regA / ((2.0).pow(valOperand.toInt())).toLong())
                regC = result
                pointeur++
            }
        }
    }

    val outRep = lstOutputs.joinToString(",")
    println(outRep)
}

