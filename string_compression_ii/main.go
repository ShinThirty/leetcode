package main

var solutions map[int]map[int]int

func getLengthOfOptimalCompression(s string, k int) int {
	solutions = make(map[int]map[int]int)
	return solve(s, 0, k)
}

func getCompressedLength(batch_length int) int {
	if batch_length == 1 {
		return 1
	} else if batch_length < 10 {
		return 2
	} else if batch_length < 100 {
		return 3
	} else {
		return 4
	}
}

func hasSolutions(i int, k int) bool {
	_, exist := solutions[i]
	if exist {
		_, kexist := solutions[i][k]
		return kexist
	}

	return false
}

func setSolutions(i int, k int, value int) {
	_, exist := solutions[i]
	if !exist {
		solutions[i] = make(map[int]int)
	}

	solutions[i][k] = value
}

func solve(s string, i int, k int) int {
	if k < 0 {
		return 9223372036854770000
	}

	if i == len(s) {
		return 0
	}

	if !hasSolutions(i, k) {
		batchSize := 1
		sol := 9223372036854770000
		alt := 0
		c := s[i]
		j := i + 1
		for ; j < len(s); j++ {
			size := j - i
			alt = solve(s, j, k-size)
			if alt < sol {
				sol = alt
			}

			if s[j] != c {
				alt = getCompressedLength(batchSize) + solve(s, j, k-(size-batchSize))
				if alt < sol {
					sol = alt
				}
			} else {
				batchSize += 1
			}
		}

		alt = solve(s, len(s), k-(len(s)-i))
		if alt < sol {
			sol = alt
		}

		alt = getCompressedLength(batchSize) + solve(s, len(s), k-(len(s)-i-batchSize))
		if alt < sol {
			sol = alt
		}

		setSolutions(i, k, sol)
	}

	return solutions[i][k]
}
