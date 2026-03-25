package minimumaveragedifference

object Solution {
  def minimumAverageDifference(nums: Array[Int]): Int = {
    var prefix: Long = 0
    var suffix: Long = nums.foldLeft(0L)(_ + _)
    var i = 0

    var ans: Long = Int.MaxValue
    var ansi = 0
    while (i < nums.length - 1) {
      prefix += nums(i)
      suffix -= nums(i)
      val alt = (prefix / (i + 1) - suffix / (nums.length - i - 1)).abs
      if (alt < ans) {
        ans = alt
        ansi = i
      }

      i += 1
    }

    prefix += nums.last
    val alt = prefix / nums.length
    if (alt < ans) {
      ans = alt
      ansi = nums.length - 1
    }

    ansi
  }
}
