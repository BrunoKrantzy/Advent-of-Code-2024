
import java.math.BigInteger

fun canFormTargetValue(values: List<Long>, target: BigInteger): Boolean {
    // Fonction récursive pour vérifier les sous-listes avec les opérations + et *
    fun checkCombinations(nums: List<Long>, current: BigInteger): Boolean {
        if (nums.isEmpty()) return current == target // Si plus d'éléments à traiter, vérifier si on a atteint le target
        val next = nums.first() // Premier élément de la liste
        val remaining = nums.drop(1) // Liste restante après avoir pris le premier élément

        // Essayer avec l'addition
        if (checkCombinations(remaining, current + next.toBigInteger())) return true

        // Essayer avec la multiplication
        if (checkCombinations(remaining, current * next.toBigInteger())) return true

        // Aucun des deux n'a fonctionné, retourner false
        return false
    }

    // Appeler la fonction récursive sur chaque élément de la liste en tant que point de départ
    for (i in values.indices) {
        if (checkCombinations(values.filterIndexed { index, _ -> index != i }, values[i].toBigInteger())) {
            return true
        }
    }

    return false
}


fun main() {

    val inLines = readInput("input07_1")
    //val inLines = readInput("test07_1")

    var rep = 0.toBigInteger()

    inLines.forEach { line ->
        var total = line.splitLongs()[0].toBigInteger()
        var lstVal = line.splitLongs() as MutableList
        lstVal.removeFirst()

        val minVal: BigInteger = lstVal.sum().toBigInteger()
        var maxVal: BigInteger = lstVal[0].toBigInteger()
        for (v in 1 until lstVal.size) {
            maxVal *= lstVal[v].toBigInteger()
        }

        if (total==minVal || total==maxVal) {
            rep+=total
        }
        else {
            val result = canFormTargetValue(lstVal, total)

            if (result) {
                rep+=total
            }
        }
    }

    println(rep)
}
