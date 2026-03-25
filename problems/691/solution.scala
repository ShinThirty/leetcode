package `691`

import scala.collection.mutable

object Solution {
  def minStickers(stickers: Array[String], target: String): Int = {
    val f = mutable.Map.empty[Int, Int]
    f(0) = 0

    def getMinStickers(mask: Int): Int = {
      f.getOrElseUpdate(mask, computeMinStickers(mask))
    }

    def computeMinStickers(mask: Int): Int = {
      stickers.view
        .map(sticker => {
          val charToIndex = mutable.Map.empty[Char, mutable.Queue[Int]]
          var i = 0
          var curMask = mask
          while (curMask > 0) {
            val d = curMask & 1
            if (d == 1) {
              val indexes =
                charToIndex.getOrElseUpdate(target(i), mutable.Queue.empty[Int])
              indexes.enqueue(i)
            }
            curMask >>= 1
            i += 1
          }

          var nextMask = mask
          for (c <- sticker) {
            charToIndex
              .get(c)
              .foreach(indexes => {
                if (!indexes.isEmpty) {
                  val i = indexes.dequeue()
                  nextMask &= ~(1 << i)
                }
              })
          }
          nextMask
        })
        .filter(nextMask => nextMask != mask && getMinStickers(nextMask) != -1)
        .map(nextMask => getMinStickers(nextMask) + 1)
        .minOption
        .getOrElse(-1)
    }

    val n = target.length()
    val targetMask = (1 << n) - 1
    getMinStickers(targetMask)
  }
}
