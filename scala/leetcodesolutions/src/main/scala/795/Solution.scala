package `795`

object Solution {
  def numSubarrayBoundedMax(nums: Array[Int], left: Int, right: Int): Int = {
    val types = nums.map(element => {
      if (element < left) {
        0
      } else if (element > right) {
        2
      } else {
        1
      }
    })

    def getNumSubarrays(low: Int, high: Int): Int = {
      val n = (high - low).toLong
      val total = (n + 1) * n / 2
      var ans = total

      var l = low
      var r = low
      while (r < high) {
        if (types(r) == 1) {
          if (l < r) {
            val ns = r - l
            ans -= (ns + 1) * ns / 2
          }
          l = r + 1
        }
        r += 1
      }

      if (l < r) {
        val ns = r - l
        ans -= (ns + 1) * ns / 2
      }

      ans.toInt
    }

    val n = nums.length
    var l = 0
    var r = 0
    var ans = 0
    while (r < n) {
      if (types(r) == 2) {
        if (l < r) {
          ans += getNumSubarrays(l, r)
        }
        l = r + 1
      }
      r += 1
    }

    if (l < r) {
      ans += getNumSubarrays(l, r)
    }

    ans
  }
}
