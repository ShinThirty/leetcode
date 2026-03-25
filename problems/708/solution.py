
class ListNode:

    def __init__(self, val, next=None):
        self.val = val
        self.next = next


class Solution:
    """
    @param: node: a list node in the list
    @param: x: An integer
    @return: the inserted new list node
    """
    def insert(self, node, x):
        nn = ListNode(x)
        if not node:
            nn.next = nn
        else:
            start = node
            pre = node
            cur = node.next
            head, tail = None, None
            if pre != cur:
                while cur != start:
                    if pre.val <= x <= cur.val:
                        break

                    if pre.val >= cur.val:
                        tail = pre
                        head = cur

                    pre = pre.next
                    cur = cur.next
                else:
                    pre = tail
                    cur = head

            pre.next = nn
            nn.next = cur

        return nn
