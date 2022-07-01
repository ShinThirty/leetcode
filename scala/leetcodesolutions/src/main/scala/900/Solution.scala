package `900`

class RLEIterator(_encoding: Array[Int]) {

  val l = _encoding.length
  var _index = 0
  var _count = _encoding(_index)

  def next(n: Int): Int = {
    if (_index + 1 >= l) {
      -1
    } else {
      var remaining = n

      while (_index + 1 < l && remaining > _count) {
        remaining -= _count
        _index += 2
        if (_index + 1 < l) {
          _count = _encoding(_index)
        }
      }

      if (_index + 1 >= l) {
        -1
      } else {
        _count -= remaining
        _encoding(_index + 1)
      }
    }
  }

}
