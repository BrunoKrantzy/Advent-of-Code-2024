import java.util.*


// BFS grid
fun calculThePath(n: Int, m: Int, grid: Array<IntArray>, startPos: Pair<Int,Int>, endPos: Pair<Int,Int>): Int {
    // Check if the source or destination cell is blocked
    /*
    if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) {
        // Return -1 to indicate no path
        return -1
    }
    */

    // Create a queue to store the cells to explore
    val q: Queue<IntArray> = LinkedList()

    // Add the source cell to the queue and mark its distance as 0
    //q.add(intArrayOf(0, 0))
    q.add(intArrayOf(startPos.first, startPos.second))

    // Define two arrays to represent the four directions of movement
    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, 1, 0, -1)

    // Create a 2D array to store the distance of each cell
    // from the source
    val dis = Array(n) { IntArray(m) }
    for (i in 0..<n) {
        Arrays.fill(dis[i], -1)
    }

    // Set the distance of the source cell as 0
    dis[startPos.first][startPos.second] = 0

    // Loop until the queue is empty or the destination is reached
    while (!q.isEmpty()) {
        // Get the front cell from the queue and remove it
        val p = q.poll()
        val x = p[0]
        val y = p[1]

        // Loop through the four directions of movement
        for (i in 0..3) {
            // Calculate the coordinates of the neighboring cell
            val xx = x + dx[i]
            val yy = y + dy[i]

            // Check if the neighboring cell is inside the grid
            // and not visited before
            if (xx >= 0 && xx < n && yy >= 0 && yy < m && dis[xx][yy] == -1) {
                // Check if the neighboring cell is free or special
                if (grid[xx][yy] == 0 || grid[xx][yy] == 2) {
                    // Set the distance of the neighboring cell as one
                    // more than the current cell
                    dis[xx][yy] = dis[x][y] + 1

                    // Add the neighboring cell to the queue for
                    // further exploration
                    q.add(intArrayOf(xx, yy))
                }
            }
        }
    }

    // Return the distance of the destination cell from the source
    //return dis[n - 1][m - 1]
    return dis[endPos.first][endPos.second]
}


fun main() {

    val inLines = readInput("input20_1")
    //val inLines = readInput("test20_1")

    var nbL = inLines.size
    var nbC = inLines[0].length

    var scoreV = 99 // 0
    var rep = 0
    var path = 0
    var posStart = Pair(0, 0)
    var posEnd = Pair(0, 0)
    var tabInput = Array(nbL) { IntArray(nbC) { 0 } }
    var cellEnCours = Pair(-1, -1)

    inLines.forEachIndexed { idxL, ligne ->
        ligne.forEachIndexed { idxC, car ->
            when (car) {
                '.' -> tabInput[idxL][idxC] = 0
                '#' -> tabInput[idxL][idxC] = 1
                'S' -> {
                    posStart = Pair(idxL, idxC)
                    tabInput[idxL][idxC] = 0
                }
                'E' -> {
                    posEnd = Pair(idxL, idxC)
                    tabInput[idxL][idxC] = 0
                }
            }
        }
    }

    var pathInitial = calculThePath(nbL, nbC, tabInput, posStart, posEnd)
    println("initial : $pathInitial")

    for (l in 1 .. nbL-1) {
        for (c in 1 .. nbC-1) {
            if (tabInput[l][c] == 1) {
                cellEnCours = Pair(l, c)
                tabInput[cellEnCours.first][cellEnCours.second] = 0
                path = calculThePath(nbL, nbC, tabInput, posStart, posEnd)
                if (path != -1) {
                    var dif = pathInitial - path
                    if (dif > scoreV)
                        rep++
                    }
                tabInput[cellEnCours.first][cellEnCours.second] = 1
            }
        }
    }

    println("rep : $rep")
}
