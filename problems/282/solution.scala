package `282`

import scala.collection.mutable

object Solution {
  def addOperators(num: String, target: Int): List[String] = {
    val operands = mutable.ArrayDeque.empty[Long]
    val operators = mutable.ArrayDeque.empty[Byte]
    val priorities: Map[Byte, Byte] = Map(
      0.toByte -> 0.toByte,
      1.toByte -> 0.toByte,
      2.toByte -> 1.toByte
    )
    val symbols: Map[Byte, String] = Map(
      0.toByte -> "+",
      1.toByte -> "-",
      2.toByte -> "*"
    )

    def add(a: Long, b: Long): Long = {
      a + b
    }

    def subtract(a: Long, b: Long): Long = {
      a - b
    }

    def multiply(a: Long, b: Long): Long = {
      a * b
    }

    val calculate: Map[Byte, (Long, Long) => Long] = Map(
      0.toByte -> add,
      1.toByte -> subtract,
      2.toByte -> multiply
    )

    val results = mutable.Buffer.empty[String]

    def explore(start: Int): Unit = {
      if (start == num.length()) {
        verify(operands, operators).foreach(results += _)
      } else if (num(start) == '0') {
        var operand = 0
        operands.append(operand)
        if (start < num.length() - 1) {
          for (operator <- 0 to 2) {
            operators.append(operator.toByte)
            explore(start + 1)
            operators.removeLast()
          }
        } else {
          explore(start + 1)
        }
        operands.removeLast()
      } else {
        var operand = 0
        for (end <- start + 1 to num.length() - 1) {
          operand = operand * 10 + num(end - 1).asDigit
          operands.append(operand)
          for (operator <- 0 to 2) {
            operators.append(operator.toByte)
            explore(end)
            operators.removeLast()
          }
          operands.removeLast()
        }

        // end == num.length()
        operand = operand * 10 + num(num.length() - 1).asDigit
        operands.append(operand)
        explore(num.length())
        operands.removeLast()
      }
    }

    def verify(
        operands: mutable.Seq[Long],
        operators: mutable.Seq[Byte]
    ): Option[String] = {
      val operandsLocal = mutable.Stack.empty[Long]
      val operatorsLocal = mutable.Stack.empty[Byte]
      val expression = mutable.Buffer.empty[String]

      var i = 0
      while (i < operands.length - 1) {
        operandsLocal.push(operands(i))
        expression += operands(i).toString()
        val currentOperator = operators(i)

        while (
          operatorsLocal.length > 0 && priorities(
            operatorsLocal.top
          ) >= priorities(currentOperator)
        ) {
          val op = operatorsLocal.pop()
          val op2 = operandsLocal.pop()
          val op1 = operandsLocal.pop()
          val res = calculate(op)(op1, op2)
          operandsLocal.push(res)
        }

        operatorsLocal.push(currentOperator)
        expression += symbols(currentOperator)
        i += 1
      }

      operandsLocal.push(operands(operands.length - 1))
      expression += operands(i).toString()
      while (operandsLocal.length > 1) {
        val op = operatorsLocal.pop()
        val op2 = operandsLocal.pop()
        val op1 = operandsLocal.pop()
        val res = calculate(op)(op1, op2)
        operandsLocal.push(res)
      }

      if (operandsLocal.top == target) {
        Some(expression.mkString)
      } else {
        None
      }
    }

    explore(0)

    results.toList
  }
}
