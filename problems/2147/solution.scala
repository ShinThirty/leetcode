package numberofwaystodevicealongcorridor

import scala.collection.mutable.Buffer

object Solution {
  def numberOfWays(corridor: String): Int = {
    val groups: Buffer[(Int, Int)] = Buffer()

    var start = 0
    var end = -1
    var nSeats = 0
    var i = 0
    var valid = false
    for (c <- corridor) {
      if (c == 'S') {
        if (nSeats == 0) {
          valid = false
          start = i
        }
        nSeats += 1
      }
      if (nSeats == 2) {
        valid = true
        end = i
        groups += Tuple2(start, end)
        nSeats = 0
      }
      i += 1
    }

    if (nSeats == 1 || !valid) {
      0
    } else {
      var lastEnd = -1
      var ans = 1L
      val mod = 1000000007
      for ((start, end) <- groups) {
        if (lastEnd != -1) {
          val interval = start - lastEnd
          println(interval)
          ans = (ans * interval) % mod
        }
        lastEnd = end
      }

      ans.toInt
    }
  }
}
