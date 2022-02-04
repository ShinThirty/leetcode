package main

func stoneGameV(stoneValue []int) int {
	p := make([]int, 1+len(stoneValue))

	// sum(i..j-1) == p[j] - p[i]
	p[0] = 0
	for i := 1; i < len(stoneValue)+1; i++ {
		p[i] = p[i-1] + stoneValue[i-1]
	}

	solutions := make([][]int, len(stoneValue)+1)
	// i < j
	// sideMax[i][j] = max(p[j] - p[i] + solutions[i][j])
	// sideMax[j][i] = max(p[j] - p[i] + solutions[i][j])
	// sideMax[i][j] = max(sideMax[i][j - 1], p[j] - p[i] + solutions[i][j])
	// sideMax[j][i] = max(sideMax[j][i + 1], p[j] - p[i] + solutions[i][j])
	sideMax := make([][]int, len(stoneValue)+1)
	for i := 0; i < len(solutions); i++ {
		solutions[i] = make([]int, len(stoneValue)+1)
		sideMax[i] = make([]int, len(stoneValue)+1)
	}

	for i := len(solutions) - 1; i >= 0; i-- {
		// first index where sum(i..k-1) >= sum(k..j-1)
		// solutions[i][j] = max(sideMax[i][k-1], sideMax[j][k])
		k := i + 1

		for j := i + 1; j < len(solutions); j++ {
			for k < j && p[k]-p[i] < p[j]-p[k] {
				k++
			}

			if p[k]-p[i] == p[j]-p[k] {
				solutions[i][j] = p[k] - p[i] + solutions[i][k]
				if p[j]-p[k]+solutions[k][j] > solutions[i][j] {
					solutions[i][j] = p[j] - p[k] + solutions[k][j]
				}
			}

			if solutions[i][j] < sideMax[i][k-1] {
				solutions[i][j] = sideMax[i][k-1]
			}
			if solutions[i][j] < sideMax[j][k] {
				solutions[i][j] = sideMax[j][k]
			}

			altSideMax := p[j] - p[i] + solutions[i][j]
			sideMax[i][j] = sideMax[i][j-1]
			if altSideMax > sideMax[i][j] {
				sideMax[i][j] = altSideMax
			}
			sideMax[j][i] = sideMax[j][i+1]
			if altSideMax > sideMax[j][i] {
				sideMax[j][i] = altSideMax
			}
		}
	}

	return solutions[0][len(stoneValue)]
}
