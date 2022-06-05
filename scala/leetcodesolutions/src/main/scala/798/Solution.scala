package `798`

import scala.collection.mutable

object Solution {
  def bestRotation(nums: Array[Int]): Int = {
    val l = nums.length
    val rotationNumberIntervals = mutable.Buffer.empty[Tuple2[Int, Int]]

    nums.zipWithIndex.foreach {
      case (num, idx) => {
        if (num <= idx) {
          val interval = (0, idx - num)
          rotationNumberIntervals += interval
          if (idx < l - 1) {
            val interval = (idx + 1, l - 1)
            rotationNumberIntervals += interval
          }
        } else {
          val interval = (idx + 1, idx + l - num)
          rotationNumberIntervals += interval
        }
      }
    }

    val rotationToScoreChanges = Array.fill(l + 1)(0)
    rotationNumberIntervals.foreach {
      case (start, end) => {
        rotationToScoreChanges(start) += 1
        rotationToScoreChanges(end + 1) -= 1
      }
    }

    var maxScore = 0
    var smallestRotations = nums.length

    rotationToScoreChanges.view.zipWithIndex.foldLeft(0) {
      case (prevScore, (scoreChange, rotationNumber)) => {
        val currentScore = prevScore + scoreChange
        if (currentScore > maxScore) {
          maxScore = currentScore
          smallestRotations = rotationNumber
        }
        currentScore
      }
    }

    smallestRotations
  }
}
