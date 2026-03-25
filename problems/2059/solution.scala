package minimumoperationstoconvertnumber

import scala.collection.mutable

object Solution {
  def minimumOperations(nums: Array[Int], start: Int, goal: Int): Int = {
    def rangeCheck: Int => Boolean = num => 0 <= num && num <= 1000

    val frontier = mutable.Queue.empty[Int]
    val black = mutable.Set.empty[Int]
    frontier.append(start)
    black += start
    var level = -1
    var found = false
    while (frontier.nonEmpty && !found) {
      level += 1
      var i = 0
      val l = frontier.length
      while (i < l && !found) {
        val cur = frontier.removeHead()
        if (cur == goal) {
          found = true
        } else if (rangeCheck(cur)) {
          for (num <- nums) {
            List(cur + num, cur - num, cur ^ num).view
              .filter(alt => !black.contains(alt))
              .foreach(alt => {
                black += alt
                frontier.append(alt)
              })
          }
        }
        i += 1
      }
    }

    if (found) level else -1
  }
}
