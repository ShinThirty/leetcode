package `395`

import scala.collection.mutable

/**
  * We need to iterate through all possible substrings and validate each.
  * To iterate through all possible substrings, we can use two pointers
  * to track the number of unique characters in a substring.
  * Define the number of unique characters of the whole string to be M.
  * We can explore all possible substrings with certain number of unique characters in the interval
  * [1, M].
  * We maintain a dynamic set of characters which occurred at least k times during the iteration.
  */
object Solution {
  def longestSubstring(s: String, k: Int): Int = {
    val characters = mutable.Set.empty[Char]
    s.foreach(c => characters += c)
    val uniqueChars = characters.size
    val n = s.length()

    def longestValidSubStringWithNUniqueCharacters(nUnique: Int): Int = {
      val charCounts = mutable.Map.empty[Char, Int]
      var l = 0
      var r = 0

      var maxLengthValidString = 0
      val charactersOccurredAtLeastK = mutable.Set.empty[Char]
      while (r < n) {
        if (charCounts.size <= nUnique) {
          if (charCounts.size == nUnique && charactersOccurredAtLeastK.size == nUnique) {
            maxLengthValidString = Math.max(maxLengthValidString, r - l)
          }
          val oldCount = charCounts.getOrElseUpdate(s(r), 0)
          charCounts += ((s(r), oldCount + 1))
          if (charCounts(s(r)) >= k) {
            charactersOccurredAtLeastK += s(r)
          }
          r += 1
        } else {
          val oldCount = charCounts(s(l))
          if (oldCount == 1) {
            charCounts -= s(l)
          } else {
            charCounts += ((s(l), oldCount - 1))
          }
          if (charCounts.getOrElse(s(l), 0) < k) {
            charactersOccurredAtLeastK -= s(l)
          }
          l += 1
        }
      }

      while (charCounts.size >= nUnique) {
        if (charCounts.size == nUnique && charactersOccurredAtLeastK.size == nUnique) {
          maxLengthValidString = Math.max(maxLengthValidString, r - l)
        }

        val oldCount = charCounts(s(l))
        if (oldCount == 1) {
          charCounts -= s(l)
        } else {
          charCounts += ((s(l), oldCount - 1))
        }
        if (charCounts.getOrElse(s(l), 0) < k) {
          charactersOccurredAtLeastK -= s(l)
        }
        l += 1
      }

      maxLengthValidString
    }

    Range
      .inclusive(1, uniqueChars)
      .view
      .map(uniqueChars =>
        longestValidSubStringWithNUniqueCharacters(uniqueChars)
      )
      .max
  }
}
