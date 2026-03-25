package platesbetweencandles

object Solution {
  def platesBetweenCandles(
      s: String,
      queries: Array[Array[Int]]
  ): Array[Int] = {
    val n = s.length
    val nextPlateLocation = Array.fill(n)(-1)
    val prevPlateLocation = Array.fill(n)(-1)
    val nPlates = Array.fill(n + 1)(0)

    var plateLocation = -1
    for (i <- 0 until n) {
      if (s(i) == '|') {
        plateLocation = i
      }
      prevPlateLocation(i) = plateLocation
    }

    plateLocation = -1
    for (i <- n - 1 to 0 by -1) {
      if (s(i) == '|') {
        plateLocation = i
      }
      nextPlateLocation(i) = plateLocation
    }

    for (i <- 1 to n) {
      if (i < n && s(i) == '|') {
        nPlates(i) = nPlates(i - 1) + 1
      } else {
        nPlates(i) = nPlates(i - 1)
      }
    }

    val nPlatesBetweenCandles: Array[Int] => Int = query => {
      val start = query(0)
      val end = query(1)
      val npl = nextPlateLocation(start)
      val ppl = prevPlateLocation(end)

      if (npl == -1 || ppl == -1 || npl >= ppl) {
        0
      } else {
        // npl < ppl
        ppl - npl - nPlates(ppl) + nPlates(npl)
      }
    }

    queries map nPlatesBetweenCandles
  }
}
