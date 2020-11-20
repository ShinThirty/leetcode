class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_word = False
        self.index = None


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def add(self, word, index):
        cur = self.root
        for c in word:
            if c not in cur.children:
                cur.children[c] = TrieNode()
            cur = cur.children[c]
        cur.is_word = True
        cur.index = index

    def find(self, word):
        index = -float('inf')
        cur = self.root
        for c in word:
            if c not in cur.children:
                return index
            cur = cur.children[c]

        def search(node):
            nonlocal index
            if node.is_word:
                index = max(index, node.index)

            for c in node.children:
                search(node.children[c])

        search(cur)
        return index


class WordFilter:
    def __init__(self, words: List[str]):
        self.trie = Trie()
        for index, word in enumerate(words):
            for i in range(len(word)):
                suffix = word[i:]
                term = suffix + "#" + word
                self.trie.add(term, index)

    def f(self, prefix: str, suffix: str) -> int:
        term = suffix + "#" + prefix
        index = self.trie.find(term)
        return index


# Your WordFilter object will be instantiated and called as such:
# obj = WordFilter(words)
# param_1 = obj.f(prefix,suffix)