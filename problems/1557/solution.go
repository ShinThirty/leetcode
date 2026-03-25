package main

func findSmallestSetOfVertices(n int, edges [][]int) []int {
	inDegrees := make([]int, n)
	for _, edge := range edges {
		v := edge[1]
		inDegrees[v] += 1
	}

	ans := make([]int, 0)
	for u, inDegree := range inDegrees {
		if inDegree == 0 {
			ans = append(ans, u)
		}
	}

	return ans
}
