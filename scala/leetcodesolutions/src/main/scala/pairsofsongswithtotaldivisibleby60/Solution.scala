package pairsofsongswithtotaldivisibleby60

import scala.collection.mutable

object Solution {
  def numPairsDivisibleBy60(time: Array[Int]): Int = {
    val remToNum = mutable.Map.empty[Int, Int]
    var res = 0

    time.foreach(t => {
      res += remToNum.getOrElse(60 - t % 60, 0)
      val idx = (t - 1) % 60 + 1
      remToNum.updateWith(idx) {
        case Some(v) => Some(v + 1)
        case None    => Some(1)
      }
    })

    res
  }
}
