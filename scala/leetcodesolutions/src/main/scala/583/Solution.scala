package `583`

object Solution {
  def minDistance(word1: String, word2: String): Int = {
    val n1 = word1.length()
    val n2 = word2.length()
    // f(i, j) = min steps of making word1(0..i - 1) and word2(0..j - 1) same
    var f = Array.fill(n2 + 1)(0)
    var g = Array.fill(n2 + 1)(0)
    for (j <- 1 to n2) {
      f(j) = j
    }

    for (i <- 1 to n1) {
      g(0) = i
      for (j <- 1 to n2) {
        if (word1(i - 1) == word2(j - 1)) {
          g(j) = f(j - 1)
        } else {
          g(j) = 1 + Math.min(g(j - 1), f(j))
        }
      }

      val t = g
      g = f
      f = t
    }

    f(n2)
  }
}
