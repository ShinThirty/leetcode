package `1998`

import scala.collection.mutable

class DisjointSet(n: Int) {
  val _parent = Array.fill(n)(-1)

  def find(x: Int): Int = {
    var root = x
    while (_parent(root) >= 0) {
      if (_parent(_parent(root)) >= 0) {
        _parent(root) = _parent(_parent(root))
      }
      root = _parent(root)
    }
    root
  }

  def union(x: Int, y: Int): Unit = {
    var xr = find(x)
    var yr = find(y)

    if (xr != yr) {
      if (_parent(xr) > _parent(yr)) {
        val t = xr
        xr = yr
        yr = t
      }

      _parent(xr) += _parent(yr)
      _parent(yr) = xr
    }
  }
}

object Solution {

  def calculatePrimeFactors(x: Int): Set[Int] = {
    val _primeFactors = mutable.Set.empty[Int]
    var i = 2
    var n = x
    while (i * i <= n) {
      if (n % i != 0) {
        i += 1
      } else {
        n /= i
        _primeFactors += i
      }
    }

    // There is at most one prime factor larger than sqrt(n)
    if (n > 1) {
      _primeFactors += n
    }

    _primeFactors.toSet
  }

  def gcdSort(nums: Array[Int]): Boolean = {
    val maxNum = nums.max
    val ds = new DisjointSet(maxNum + 1)

    nums.foreach((num) =>
      calculatePrimeFactors(num).foreach((factor) => ds.union(num, factor))
    )

    val sortedNums = nums.sorted
    var ans = true
    var i = 0
    while (ans && i < nums.length) {
      if (nums(i) != sortedNums(i)) {
        val ir = ds.find(nums(i))
        val sir = ds.find(sortedNums(i))
        if (ir != sir) {
          ans = false
        }
      }
      i += 1
    }
    ans
  }
}
