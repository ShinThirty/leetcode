package countallpossibleroutes

object Solution {
  def countRoutes(
      locations: Array[Int],
      start: Int,
      finish: Int,
      fuel: Int
  ): Int = {
    val l = locations.length
    val ans = Array.fill(l, fuel + 1)(0)
    val mod = 1000000007
    for (f <- 0 to fuel) {
      ans(finish)(f) = 1
    }

    for (f <- 1 to fuel) {
      for (idx <- 0 until l) {
        for (dest <- 0 until l) {
          if (idx != dest) {
            var dist = locations(dest) - locations(idx)
            if (dist < 0) {
              dist = -dist
            }
            if (dist <= f) {
              ans(idx)(f) = (ans(idx)(f) + ans(dest)(f - dist)) % mod
            }
          }
        }
      }
    }

    ans(start)(fuel)
  }
}
