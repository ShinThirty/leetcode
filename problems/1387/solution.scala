package sortbypowervalue

object Solution {
  def getKth(lo: Int, hi: Int, k: Int): Int = {
    def nextInt: Int => Int = n => {
      if (n % 2 == 0) {
        n / 2
      } else {
        3 * n + 1
      }
    }

    val memo = scala.collection.mutable.Map.empty[Int, Int]
    def powerOfInt: Int => Int = n => {
      if (n == 1) {
        0
      } else if (memo.contains(n)) {
        memo(n)
      } else {
        val res = powerOfInt(nextInt(n)) + 1
        memo(n) = res
        res
      }
    }

    val sorted = (lo to hi).sortBy(powerOfInt(_))
    sorted.drop(k - 1).head
  }
}
