from collections import defaultdict, deque


class Solution:
    def findLadders(self, beginWord, endWord, wordList):
        wl = len(beginWord)
        word_set = set(wordList)

        if endWord not in word_set:
            return []

        black = [{beginWord}, {endWord}]
        dist = [defaultdict(int), defaultdict(int)]
        dist[0][beginWord] = 0
        dist[1][endWord] = 0
        prev = [defaultdict(set), defaultdict(set)]
        prev[0][beginWord] = None
        prev[1][endWord] = None

        paths = []

        def transform(word, forward):
            for i in range(wl):
                for j in range(26):
                    c = chr(97 + j)
                    nw = word[:i] + c + word[i + 1:]
                    if nw not in black[forward] and nw in word_set:
                        yield nw

        def generate_paths(word, partial_path, middle_word, forward):
            if forward:
                partial_path.appendleft(word)
                if prev[0][word] is None:
                    if prev[1][middle_word] is None:
                        path = []
                        for w in partial_path:
                            path.append(w)
                        paths.append(path)
                    else:
                        for w in prev[1][middle_word]:
                            generate_paths(w, partial_path, middle_word, False)
                else:
                    for w in prev[0][word]:
                        generate_paths(w, partial_path, middle_word, True)
                partial_path.popleft()
            else:
                partial_path.append(word)
                if prev[1][word] is None:
                    path = []
                    for w in partial_path:
                        path.append(w)
                    paths.append(path)
                else:
                    for w in prev[1][word]:
                        generate_paths(w, partial_path, middle_word, False)
                partial_path.pop()

        Q = [deque(), deque()]
        Q[0].append(beginWord)
        Q[1].append(endWord)
        found = False
        while Q[0] and Q[1] and not found:
            for i in range(2):
                for _ in range(len(Q[i])):
                    cur = Q[i].popleft()
                    if cur in prev[1 - i]:
                        found = True
                        generate_paths(cur, deque(), cur, True)
                    else:
                        for nw in transform(cur, i):
                            if nw not in prev[i]:
                                dist[i][nw] = dist[i][cur] + 1
                                prev[i][nw].add(cur)
                                Q[i].append(nw)
                            elif dist[i][nw] == dist[i][cur] + 1:
                                prev[i][nw].add(cur)
                            else:
                                black[i].add(nw)

                if found:
                    break

        return paths
