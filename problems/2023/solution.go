package main

func isPrefix(num string, target string) bool {
	if len(num) >= len(target) {
		return false
	}
	return num == target[0:len(num)]
}

func isSuffix(num string, target string) bool {
	n := len(target)
	if len(num) >= len(target) {
		return false
	}
	return num == target[n-len(num):]
}

func numOfPairs(nums []string, target string) int {
	prefixCount := make(map[string]int)
	suffixCount := make(map[string]int)

	for _, num := range nums {
		if isPrefix(num, target) {
			prefixCount[num] += 1
		}

		if isSuffix(num, target) {
			suffixCount[num] += 1
		}
	}

	m := 0
	suffix := ""
	counts := 0
	ans := 0
	for prefix, countp := range prefixCount {
		m = len(prefix)
		suffix = target[m:]
		counts = suffixCount[suffix]

		if prefix == suffix {
			ans += (countp - 1) * counts
		} else {
			ans += countp * counts
		}
	}

	return ans
}
