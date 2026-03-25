package chalkreplacer

object Solution {
  def chalkReplacer(chalk: Array[Int], k: Int): Int = {
    val s = chalk.foldLeft(0L)(_ + _)
    val r = k % s
    var kk = 0
    var rr = r
    while (kk < chalk.length && rr >= chalk(kk)) {
      rr -= chalk(kk)
      kk += 1
    }

    kk
  }
}
