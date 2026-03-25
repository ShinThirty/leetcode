import scala.collection.mutable.Buffer

object Solution {
  def maxProduct(s: String): Int = {
    val n = s.length()
    val candidates = Buffer[Tuple2[Int, Int]]()
    for (mask <- 1 until 1 << n) {
      val indexes = Buffer[Byte]()
      var j = 0
      while ((1 << j) <= mask) {
        if (((1 << j) & mask) != 0) {
          indexes += j.toByte
        }
        j += 1
      }

      val m = indexes.length
      var palindromic = true
      for (mi <- 0 until m) {
        if (palindromic) {
          if (s(indexes(mi)) != s(indexes(m - 1 - mi))) {
            palindromic = false
          }
        }
      }

      if (palindromic) {
        candidates += Tuple2(mask, m)
      }
    }

    var ans = 0

    for (i <- 0 until candidates.length) {
      for (j <- i + 1 until candidates.length) {
        if ((candidates(i)._1 & candidates(j)._1) == 0) {
          ans = Math.max(ans, candidates(i)._2 * candidates(j)._2)
        }
      }
    }

    ans
  }
}
