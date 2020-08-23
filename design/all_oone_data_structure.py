class ListNode:
    def __init__(self, val):
        self.val = val
        self.keys = set()
        self.prev = None
        self.next = None


class AllOne:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.head = ListNode(-1)
        self.tail = ListNode(-1)
        self.head.next = self.tail
        self.tail.prev = self.head
        self.keytonode = {}

    def inc(self, key: str) -> None:
        """
        Inserts a new key <Key> with value 1. Or increments an existing key by 1.
        """
        cur_node = self.head
        cur_val = 0
        if key in self.keytonode:
            cur_node = self.keytonode[key]
            cur_val = cur_node.val

        next_node = cur_node.next
        if cur_val + 1 != cur_node.next.val:
            next_node = ListNode(cur_val + 1)
            next_node.prev = cur_node
            next_node.next = cur_node.next
            cur_node.next.prev = next_node
            cur_node.next = next_node

        next_node.keys.add(key)

        if key in self.keytonode:
            cur_node.keys.remove(key)
            if not cur_node.keys:
                cur_node.prev.next = cur_node.next
                cur_node.next.prev = cur_node.prev

        self.keytonode[key] = next_node

    def dec(self, key: str) -> None:
        """
        Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
        """
        if key not in self.keytonode:
            return

        cur_node = self.keytonode[key]
        cur_val = cur_node.val

        if cur_val != 1:
            prev_node = cur_node.prev
            if prev_node.val != cur_val - 1:
                prev_node = ListNode(cur_val - 1)
                prev_node.prev = cur_node.prev
                prev_node.next = cur_node
                cur_node.prev.next = prev_node
                cur_node.prev = prev_node
            prev_node.keys.add(key)
            self.keytonode[key] = prev_node
        else:
            del self.keytonode[key]

        cur_node.keys.remove(key)
        if not cur_node.keys:
            cur_node.prev.next = cur_node.next
            cur_node.next.prev = cur_node.prev

    def getMaxKey(self) -> str:
        """
        Returns one of the keys with maximal value.
        """
        if self.head.next != self.tail:
            return next(iter(self.tail.prev.keys))
        else:
            return ""

    def getMinKey(self) -> str:
        """
        Returns one of the keys with Minimal value.
        """
        if self.head.next != self.tail:
            return next(iter(self.head.next.keys))
        else:
            return ""
