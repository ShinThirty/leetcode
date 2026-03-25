package twostringequalwithencoding

import scala.collection.mutable

case class Q(i: Int, j: Int, diff: Int)

object Solution {
  def possiblyEquals(s1: String, s2: String): Boolean = {
    val n = s1.length
    val m = s2.length

    def wildcards: Int => mutable.Set[Int] = num => {
      val res = mutable.Set.empty[Int]
      res += num
      res += (num / 100) + (num % 100)
      res += (num / 10) + (num % 10)
      val a = num % 100
      res += (num / 100) + (a / 10) + (a % 10)
    }

    val answers = mutable.Map.empty[Q, Boolean]

    def verify: Q => Boolean = q => {
      answers.getOrElseUpdate(q, calculate(q))
    }

    def calculate: Q => Boolean = q => {
      var i = q.i
      var j = q.j
      val diff = q.diff
      if (i == n && j == m) {
        diff == 0
      } else {
        if (i < n && j < m && diff == 0) {
          if (!s1(i).isDigit && !s2(j).isDigit) {
            if (s1(i) != s2(j)) {
              false
            } else {
              verify(Q(i + 1, j + 1, 0))
            }
          } else {
            var num1 = 0
            while (i < n && s1(i).isDigit) {
              num1 *= 10
              num1 += s1(i).asDigit
              i += 1
            }
            if (i < n && num1 == 0) {
              num1 = 1
              i += 1
            }
            var num2 = 0
            while (j < m && s2(j).isDigit) {
              num2 *= 10
              num2 += s2(j).asDigit
              j += 1
            }
            if (j < m && num2 == 0) {
              num2 = 1
              j += 1
            }

            val wildcards1 = wildcards(num1)
            val wildcards2 = wildcards(num2)
            var res = false
            for (wildcard1 <- wildcards1; wildcard2 <- wildcards2) {
              res |= verify(Q(i, j, wildcard1 - wildcard2))
            }
            res
          }
        } else {
          if (diff > 0) {
            var num2 = 0
            while (j < m && s2(j).isDigit) {
              num2 *= 10
              num2 += s2(j).asDigit
              j += 1
            }
            if (j < m && num2 == 0) {
              num2 = 1
              j += 1
            }
            if (num2 == 0) {
              false
            } else {
              val wildcards2 = wildcards(num2)
              var res = false
              for (wildcard2 <- wildcards2) {
                res |= verify(Q(i, j, diff - wildcard2))
              }
              res
            }
          } else {
            var num1 = 0
            while (i < n && s1(i).isDigit) {
              num1 *= 10
              num1 += s1(i).asDigit
              i += 1
            }
            if (i < n && num1 == 0) {
              num1 = 1
              i += 1
            }

            if (num1 == 0) {
              false
            } else {
              val wildcards1 = wildcards(num1)
              var res = false
              for (wildcard1 <- wildcards1) {
                res |= verify(Q(i, j, diff + wildcard1))
              }
              res
            }
          }
        }
      }
    }

    verify(Q(0, 0, 0))
  }
}
