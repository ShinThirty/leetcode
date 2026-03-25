package `856`

import scala.collection.mutable

object Solution {
  def scoreOfParentheses(s: String): Int = {
    val parsingStack = mutable.ArrayDeque.empty[Int]
    parsingStack.append(0)
    for (c <- s) {
      if (c == '(') {
        parsingStack.append(0)
      } else {
        var score = parsingStack.removeLast()
        if (score == 0) {
          score = 1
        } else {
          score *= 2
        }
        parsingStack(parsingStack.length - 1) += score
      }
    }

    parsingStack(parsingStack.length - 1)
  }
}
