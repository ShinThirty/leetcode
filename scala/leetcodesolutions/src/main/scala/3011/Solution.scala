package `3011`

object Solution {
  def canSortArray(nums: Array[Int]): Boolean = {
    val n = nums.size
    if (n == 1) {
      true
    } else {
      val bits = Array.fill(n)(0)

      for (i <- 0 until n) {
        var m = nums(i)
        var count = 0
        while (m > 0) {
          count += (m & 1)
          m = m >> 1
        }
        bits(i) = count
      }

      var pmax = -1
      var cmin = nums(0)
      var cmax = nums(0)
      var i = 0 // start of current section (inclusive)
      var j = 1 // end of current section (exclusive)
      var ok = true

      while (i < n) {
        if (j < n && bits(j - 1) == bits(j)) {
          cmin = Math.min(cmin, nums(j))
          cmax = Math.max(cmax, nums(j))
          j += 1
        } else {
          if (cmin < pmax) {
            ok = false
          }
          pmax = cmax
          i = j
          j = i + 1
          if (i < n) {
            cmin = nums(i)
            cmax = nums(i)
          }
        }
      }

      ok
    }
  }
}
