package `1049`

import scala.collection.mutable

object Solution {
  def lastStoneWeightII(stones: Array[Int]): Int = {
    var possibleSums = mutable.Set.empty[Int]

    stones.foreach(value => {
      if (possibleSums.isEmpty) {
        possibleSums += value
        possibleSums += -value
      } else {
        val additionalPossibleSums = mutable.Set.empty[Int]
        possibleSums.foreach(s => {
          additionalPossibleSums += s + value
          additionalPossibleSums += s - value
        })
        possibleSums = additionalPossibleSums
      }
    })

    possibleSums.view.map(value => if (value < 0) -value else value).min
  }
}
