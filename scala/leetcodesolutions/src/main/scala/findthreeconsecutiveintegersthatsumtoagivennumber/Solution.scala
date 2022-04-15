package findthreeconsecutiveintegersthatsumtoagivennumber

object Solution {
  def sumOfThree(num: Long): Array[Long] = {
    val q = num / 3
    val p = num % 3
    if (p != 0) {
      Array[Long]()
    } else {
      Array[Long](q - 1, q, q + 1)
    }
  }
}
