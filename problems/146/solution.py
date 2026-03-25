class ListNode:
    def __init__(self, key):
        self.key = key
        self.next = None
        self.prev = None


class LRUList:
    def __init__(self):
        self.head = ListNode(None)
        self.tail = ListNode(None)
        self.head.next = self.tail
        self.tail.prev = self.head

    def append(self, node):
        node.next = self.head.next
        node.prev = self.head
        node.next.prev = node
        node.prev.next = node

    def remove(self, node):
        node.next.prev, node.prev.next = node.prev, node.next

    def pop(self):
        node = self.tail.prev
        self.remove(node)
        return node

    def replaceleft(self, node):
        self.remove(node)
        self.append(node)


class LRUCache:
    """OrderedDict, LinkedHashMap or any container that can remember the
    sequence of the data being added can be used to implement a LRU cache.
    """

    def __init__(self, capacity: int):
        self.c = capacity
        self.lru = LRUList()
        self.data = {}

    def get(self, key: int) -> int:
        if key not in self.data:
            return None

        val, node = self.data[key]
        self.lru.replaceleft(node)
        return val

    def put(self, key: int, value: int) -> None:
        if key in self.data:
            val, node = self.data[key]
            self.lru.replaceleft(node)
            self.data[key] = (value, node)
        else:
            node = ListNode(key)
            self.lru.append(node)
            self.data[key] = (value, node)
            self.c -= 1
            if self.c < 0:
                node = self.lru.pop()
                del self.data[node.key]
                self.c += 1


# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)