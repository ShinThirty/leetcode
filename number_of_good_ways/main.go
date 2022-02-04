package main

func numSplits(s string) int {
	countLeft := make(map[byte]int)
	countRight := make(map[byte]int)

	for i := 0; i < len(s); i++ {
		c := s[i]
		countRight[c] += 1
	}

	ans := 0

	for p := 0; p < len(s)-1; p++ {
		c := s[p]
		countLeft[c] += 1
		countRight[c] -= 1
		if countRight[c] == 0 {
			delete(countRight, c)
		}

		if len(countRight) == len(countLeft) {
			ans += 1
		}
	}

	return ans
}
