class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        s1 = []
        s2 = []
        n1 = l1
        n2 = l2
        while n1:
            s1.append(n1.val)
            n1 = n1.next
        while n2:
            s2.append(n2.val)
            n2 = n2.next

        if len(s1) < len(s2):
            s1, s2 = s2, s1

        carry = 0
        head = None
        while s1:
            op1 = s1.pop()
            op2 = 0
            if s2:
                op2 = s2.pop()
            s = op1 + op2 + carry
            r = s % 10
            carry = s // 10
            node = ListNode(r)
            node.next = head
            head = node

        if carry != 0:
            node = ListNode(carry)
            node.next = head
            head = node

        return head
