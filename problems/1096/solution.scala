package braceexpansionii

import scala.collection.mutable

case class State(res: mutable.Set[String], var prev: mutable.Set[String])

object Solution {
  def braceExpansionII(expression: String): List[String] = {
    val stack = mutable.Stack.empty[State]
    stack.push(State(mutable.Set.empty[String], null))
    var element = new mutable.StringBuilder()

    for (c <- expression + "#") {
      if (c.isLower) {
        element.append(c)
      } else {
        if (element.nonEmpty) {
          val s = element.toString()
          if (stack.top.prev == null) {
            stack.top.prev = mutable.Set.fill(1)(s)
          } else {
            val nPrev = stack.top.prev.map(p => p + s)
            stack.top.prev = nPrev
          }
          element = new mutable.StringBuilder()
        }
        if (c == '{') {
          stack.push(State(mutable.Set.empty[String], null))
        } else {
          stack.top.prev.foreach(p => stack.top.res.addOne(p))
          stack.top.prev = null
          if (c == '}') {
            val lastLevelRes = stack.pop().res
            if (stack.top.prev == null) {
              stack.top.prev = lastLevelRes
            } else {
              val nPrev = mutable.Set.empty[String]
              for (s1 <- stack.top.prev; s2 <- lastLevelRes) {
                nPrev.addOne(s1 + s2)
              }
              stack.top.prev = nPrev
            }
          }
        }
      }
    }
    stack.top.res.toList.sorted
  }
}
