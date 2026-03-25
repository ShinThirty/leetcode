"""
Definition of TreeNode:
class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left, self.right = None, None
"""


class Solution:
    """
    @param root: the given BST
    @param target: the given target
    @param k: the given k
    @return: k values in the BST that are closest to the target
    """
    def closestKValues(self, root, target, k):
        sn, sp = [], []
        cur = root
        while cur:
            if cur.val <= target:
                sp.append(cur)
                cur = cur.right
            else:
                sn.append(cur)
                cur = cur.left

        def nxt():
            if sn:
                cur = sn.pop()
                n = cur.right
                if n:
                    sn.append(n)
                    while n.left:
                        n = n.left
                        sn.append(n)
                return cur.val
            else:
                return float('inf')

        def pre():
            if sp:
                cur = sp.pop()
                p = cur.left
                if p:
                    sp.append(p)
                    while p.right:
                        p = p.right
                        sp.append(p)
                return cur.val
            else:
                return -float('inf')

        length = 0
        nv = nxt()
        pv = pre()
        output = []
        while length < k:
            dn = nv - target
            dp = target - pv
            if dn <= dp:
                output.append(nv)
                nv = nxt()
            else:
                output.append(pv)
                pv = pre()
            length += 1

        return output
