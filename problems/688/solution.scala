package `688`

object Solution {
  def knightProbability(n: Int, k: Int, row: Int, column: Int): Double = {
    if (k == 0) {
      1.0
    } else {
      val deltas = Array(
        (-1, -2),
        (-2, -1),
        (-2, 1),
        (-1, 2),
        (1, -2),
        (2, -1),
        (1, 2),
        (2, 1)
      )

      var prevResults = Array.fill(n, n)(1.0)
      var curResults = Array.fill(n, n)(0.0)

      for (step <- 1 to k - 1) {
        for (r <- 0 until n) {
          for (c <- 0 until n) {
            curResults(r)(c) = deltas.view
              .map({ case (dr, dc) => (r + dr, c + dc) })
              .filter({ case (pr, pc) =>
                0 <= pr && pr < n && 0 <= pc && pc < n
              })
              .map({ case (pr, pc) => prevResults(pr)(pc) })
              .sum / 8
          }
        }

        prevResults = curResults
        curResults = Array.fill(n, n)(0.0)
      }

      deltas.view
        .map({ case (dr, dc) => (row + dr, column + dc) })
        .filter({ case (pr, pc) => 0 <= pr && pr < n && 0 <= pc && pc < n })
        .map({ case (pr, pc) => prevResults(pr)(pc) })
        .sum / 8
    }
  }
}
