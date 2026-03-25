package makesumdivisiblebyp

object Solution {
  def minSubarray(nums: Array[Int], p: Int): Int = {
    val r = nums.reduce((a, b) => (a + b) % p) % p
    if (r == 0) 0 else caculate(nums, p, r)
  }

  def caculate(nums: Array[Int], p: Int, r: Int): Int = {
    val premodToIndex = scala.collection.mutable.Map[Int, Int]()
    premodToIndex(0) = -1
    var mod = 0
    val ans = nums.view.zipWithIndex.foldLeft(nums.length) {
      case (ans, (num, i)) => {
        mod = (mod + num) % p
        val prev = (mod - r + p) % p
        val alt = premodToIndex.get(prev).flatMap((j) => Some(i - j))
        premodToIndex(mod) = i

        alt match {
          case None        => ans
          case Some(value) => if (value < ans) value else ans
        }
      }
    }

    if (ans != nums.length) ans else -1
  }
}
