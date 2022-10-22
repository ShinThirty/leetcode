package balancedstring

import scala.collection.mutable

object Solution {
  def balancedString(s: String): Int = {
    val countsByCharacter = mutable.Map.from(s.groupMapReduce(c => c)(c => 1)(_ + _))
    val n = s.length
    val maxNumberOfCharacters = n / 4
    var i = 0
    var j = 0

    def valid: () => Boolean = () => countsByCharacter.foldLeft(true) {
      case (res, (_, num)) => if (num > maxNumberOfCharacters) false else res
    }

    var answer = n
    while (i < n && j <= n) {
      if (valid()) {
        answer = Math.min(answer, j - i)
        val old = countsByCharacter.getOrElse(s(i), 0)
        countsByCharacter.put(s(i), old + 1)
        i += 1

      } else {
        if (j < n) {
          val old = countsByCharacter.getOrElse(s(j), 0)
          countsByCharacter.put(s(j), old - 1)
        }
        j += 1
      }
    }

    answer

  }
}