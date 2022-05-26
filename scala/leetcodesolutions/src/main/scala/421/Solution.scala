package `421`

import scala.collection.mutable

class TrieNode(val children: mutable.Map[Boolean, TrieNode]) {
  def getChildOrUpdate(indicator: Boolean): TrieNode = {
    children.getOrElseUpdate(indicator, TrieNode())
  }

  def hasChild(indicator: Boolean): Boolean = {
    children.contains(indicator)
  }
}

object TrieNode {
  def apply(): TrieNode = {
    new TrieNode(mutable.Map.empty[Boolean, TrieNode])
  }
}

class Trie {
  var _root = TrieNode()
  val _fac = 1 << 30

  def addNum(num: Int): Unit = {
    var fac = _fac
    var cur = _root
    while (fac > 0) {
      val indicator = (num & fac) != 0
      val child = cur.getChildOrUpdate(indicator)
      cur = child
      fac = fac >> 1
    }
  }

  def getMaximumXOR(op2: Int): Int = {
    var fac = _fac
    var cur = _root
    var ans = 0
    while (fac > 0) {
      ans = ans << 1
      val indicator = (op2 & fac) == 0
      val hasChild = cur.hasChild(indicator)
      if (hasChild) {
        ans += 1
        cur = cur.getChildOrUpdate(indicator)
      } else {
        ans += 0
        cur = cur.getChildOrUpdate(!indicator)
      }

      fac = fac >> 1
    }

    ans
  }
}

object Solution {
  def findMaximumXOR(nums: Array[Int]): Int = {
    val trie = new Trie()
    nums.foldLeft(0) {
      case (accu, num) => {
        trie.addNum(num)
        val alt = trie.getMaximumXOR(num)
        Math.max(alt, accu)
      }
    }
  }
}
