class Solution:
    def expressiveWords(self, S, words):
        stack = []
        for c in S:
            if not stack:
                stack.append((c, 1))
            else:
                if stack[-1][0] == c:
                    tc, tcount = stack.pop()
                    stack.append((c, tcount + 1))
                else:
                    stack.append((c, 1))

        def check(word):
            s = []
            for c in word:
                if not s:
                    s.append((c, 1))
                else:
                    if s[-1][0] == c:
                        tc, tcount = s.pop()
                        s.append((c, tcount + 1))
                    else:
                        i = len(s) - 1
                        if s[i][0] != stack[i][0]:
                            return False
                        else:
                            if stack[i][1] >= 3 and stack[i][1] >= s[i][1]:
                                pass
                            elif stack[i][1] == s[i][1]:
                                pass
                            else:
                                return False
                        s.append((c, 1))

            i = len(s) - 1
            if len(s) != len(stack):
                return False
            elif s[i][0] != stack[i][0]:
                return False
            else:
                if stack[i][1] >= 3 and stack[i][1] >= s[i][1]:
                    pass
                elif stack[i][1] == s[i][1]:
                    pass
                else:
                    return False

            return True

        count = 0
        for word in words:
            count += check(word)

        return count
