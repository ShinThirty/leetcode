class Solution:
    def fullJustify(self, words, maxWidth):
        n = len(words)
        output = []

        def justify(start, end, rw):
            k = end - start - 1
            space = (maxWidth - rw) // k
            remainder = (maxWidth - rw) % k

            line = []
            line.append(words[start])
            for i in range(start + 1, end):
                line.append(" " * space)
                if remainder > 0:
                    line.append(" ")
                    remainder -= 1
                line.append(words[i])

            output.append("".join(line))

        def justify_left(start, end):
            line_length = 0
            line = []
            line.append(words[start])
            line_length += len(words[start])
            for i in range(start + 1, end):
                line.append(" ")
                line.append(words[i])
                line_length += len(words[i]) + 1
            line.append(" " * (maxWidth - line_length))
            output.append("".join(line))

        end = 0
        rs = -1
        rw = 0
        start = 0

        while end < n:
            nrs = rs + len(words[end]) + 1
            if nrs <= maxWidth:
                rs = nrs
                rw += len(words[end])
                end += 1
            else:
                if end - start == 1:
                    justify_left(start, end)
                else:
                    justify(start, end, rw)
                rs = -1
                rw = 0
                start = end

        if start < end:
            justify_left(start, end)

        return output
