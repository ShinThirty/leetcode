package findinmountainarray


class MountainArray {
  def get(index: Int): Int = index
  def length: Int = 0
}

object Solution {
  def findInMountainArray(value: Int, mountainArr: MountainArray): Int = {
    def findPeak: Int => Int => Int = i => j => {
      val m = i + (j - i) / 2
      val p = if (m > 0) mountainArr.get(m - 1) else Int.MinValue
      val c = mountainArr.get(m)
      val n = if (m < mountainArr.length - 1) mountainArr.get(m + 1) else Int.MinValue
      if (p < c && c > n) {
        m
      } else {
        if (p < c && c < n) {
          findPeak(m + 1)(j)
        } else {
          findPeak(i)(m - 1)
        }
      }
    }

    def findElement: Int => Int => Int = i => j => {
      if (i > j) {
        -1
      } else {
        val m = i + (j - i) / 2
        if (value == mountainArr.get(m)) {
          m
        } else {
          if (value < mountainArr.get(m)) {
            findElement(i)(m - 1)
          } else {
            findElement(m + 1)(j)
          }
        }
      }
    }

    def findReverseElement: Int => Int => Int = i => j => {
      if (i > j) {
        -1
      } else {
        val m = i + (j - i) / 2
        if (value == mountainArr.get(m)) {
          m
        } else {
          if (value > mountainArr.get(m)) {
            findReverseElement(i)(m - 1)
          } else {
            findReverseElement(m + 1)(j)
          }
        }
      }
    }

    val peak = findPeak(0)(mountainArr.length - 1)
    val possible1 = findElement(0)(peak)
    val possible2 = findReverseElement(peak)(mountainArr.length - 1)
    if (possible1 == -1) possible2 else possible1
  }
}