package `1458`

object Solution {
  def maxDotProduct(nums1: Array[Int], nums2: Array[Int]): Int = {
    val l1 = nums1.length
    val l2 = nums2.length
    var last = Array.fill(l2 + 1)(Int.MinValue)
    var cur = Array.fill(l2 + 1)(Int.MinValue)

    for (i <- 1 to l1) {
      for (j <- 1 to l2) {
        val dotProduct = nums1(i - 1) * nums2(j - 1)
        val alt0 = last(j - 1)
        val alt1 = Math.max(0, alt0) + dotProduct
        val alt2 = last(j)
        val alt3 = cur(j - 1)
        val alts = Array(alt0, alt1, alt2, alt3)
        cur(j) = alts.max
      }
      val temp = last
      last = cur
      cur = temp
    }

    last(l2)
  }
}
