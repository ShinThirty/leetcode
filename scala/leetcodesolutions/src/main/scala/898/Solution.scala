package `898`

import scala.collection.mutable

object Solution {
  def subarrayBitwiseORs(arr: Array[Int]): Int = {
    val ans = mutable.Set.empty[Int]
    var prev = mutable.Set.empty[Int]
    arr.foreach(num => {
      val cur = mutable.Set.empty[Int]
      cur += num
      ans += num
      prev.foreach(res => {
        cur += res | num
        ans += res | num
      })
      prev = cur
    })
    ans.size
  }
}
