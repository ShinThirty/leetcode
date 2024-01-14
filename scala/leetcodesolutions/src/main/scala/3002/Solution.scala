package `3002`

object Solution {
  def maximumSetSize(nums1: Array[Int], nums2: Array[Int]): Int = {
    val n = nums1.size
    val t = n / 2
    val sets1 = nums1.toSet
    val sets2 = nums2.toSet
    val intersection = sets1.intersect(sets2)
    val numSameElements = intersection.size
    val diff1 = sets1.size - numSameElements
    val diff2 = sets2.size - numSameElements

    val contrib1 = if (diff1 >= t) t else diff1
    val contrib2 = if (diff2 >= t) t else diff2

    Math.min(contrib1 + contrib2 + numSameElements, n)
  }
}
