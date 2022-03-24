package bitset

import scala.collection.mutable.ArrayBuffer

class Bitset(_size: Int) {

  private val _bitset = ArrayBuffer.fill(_size)(false)
  private var _nOnes = 0
  private var _flipped = false

  def fix(idx: Int): Unit = {
    if (_bitset(idx) == _flipped) {
      _nOnes += 1
    }
    _bitset(idx) = !_flipped
  }

  def unfix(idx: Int): Unit = {
    if (_bitset(idx) == !_flipped) {
      _nOnes -= 1
    }
    _bitset(idx) = _flipped
  }

  def flip(): Unit = {
    _flipped = !_flipped
    _nOnes = _size - _nOnes
  }

  def all(): Boolean = _size == _nOnes

  def one(): Boolean = _nOnes > 0

  def count(): Int = _nOnes

  override def toString(): String = {
    _bitset.map(bit => if (bit ^ _flipped) 1 else 0).mkString
  }

}

/** Your Bitset object will be instantiated and called as such: var obj = new
  * Bitset(size) obj.fix(idx) obj.unfix(idx) obj.flip() var param_4 = obj.all()
  * var param_5 = obj.one() var param_6 = obj.count() var param_7 =
  * obj.toString()
  */
