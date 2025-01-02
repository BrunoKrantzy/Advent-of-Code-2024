

fun main() {

    val inLines = readInput("input19_1")
    //val inLines = readInput("test19_1")

    var rep = 0
    var mapPatterns = mutableMapOf<String, String>()
    var lstDesigns = mutableListOf<String>()

    inLines.forEachIndexed { idx, line ->
        if (idx == 0) {
            var lstPatterns = line.split(", ") as MutableList
            lstPatterns.sort()

            lstPatterns.forEach { pat ->
                mapPatterns[pat] = pat
            }
        }
        else if (idx > 1) {
            lstDesigns.add(line)
        }
    }

    // decoupage design pour produire un set des mots correspond. à un pattern
    var setMotsAvecPattern = mutableSetOf<String>()
    lstDesigns.forEach { design ->
        val lgD = design.length
        for (let in 0 until lgD) {
            for (i in let until lgD) {
                var mot = design.substring(let..i)
                if (mapPatterns.containsKey(mot))
                    setMotsAvecPattern.add(mot)
            }
        }

        // fab du pattern pour le regex
        var pattern = StringBuilder()
        pattern.append("(")
        setMotsAvecPattern.forEachIndexed { idx, it ->
            pattern.append(it)
            if (idx < setMotsAvecPattern.size-1)
                pattern.append("|")
        }
        pattern.append(")*")

        // test du design
        val patReg = pattern.toString().toRegex()
        if (patReg.matchEntire(design) != null)
            rep++
    }

    println(rep)
}

