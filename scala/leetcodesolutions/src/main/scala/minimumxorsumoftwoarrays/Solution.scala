package minimumxorsumoftwoarrays

import scala.collection.mutable

object Solution {
  def minimumXORSum(nums1: Array[Int], nums2: Array[Int]): Int = {
    val n = nums1.length
    val solutions = mutable.Buffer.fill(n)(mutable.Map.empty[Int, Int])

    def solve(i: Int, used: Int): Int = {
      if (i < n) {
        solutions(i).getOrElseUpdate(used, compute(i, used))
      } else {
        0
      }
    }

    def compute(i: Int, used: Int): Int = {
      var j = n - 1
      var m = 1 << j
      var ans = Int.MaxValue
      while (j >= 0) {
        if ((used & m) == 0) {
          var alt = (nums1(i) ^ nums2(j)) + solve(i + 1, used | m)
          if (alt < ans) {
            ans = alt
          }
        }
        m >>= 1
        j -= 1
      }

      ans
    }

    solve(0, 0)
  }
}
