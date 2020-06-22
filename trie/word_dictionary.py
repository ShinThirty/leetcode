class TrieNode:
    def __init__(self):
        self.is_word = False
        self.children = {}


class WordDictionary:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root = TrieNode()

    def addWord(self, word: str) -> None:
        """
        Adds a word into the data structure.
        """
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]
        node.is_word = True

    def search(self, word: str) -> bool:
        """
        Returns if the word is in the data structure. A word could contain the dot
        character '.' to represent any one letter.
        """
        def _search(node, i):
            if i == len(word):
                return node.is_word
            else:
                if word[i] == ".":
                    for c in node.children:
                        if _search(node.children[c], i + 1):
                            return True
                    else:
                        return False
                else:
                    c = i
                    n = node
                    while c < len(word) and word[c] != ".":
                        if word[c] not in n.children:
                            return False
                        n = n.children[word[c]]
                        c += 1

                    return _search(n, c)

        return _search(self.root, 0)


def run():
    w = WordDictionary()
    w.addWord("bad")
    w.addWord("dad")
    w.addWord("mad")
    w.search("pad")
    w.search("bad")
    w.search(".ad")
    w.search("b..")


if __name__ == "__main__":
    run()
