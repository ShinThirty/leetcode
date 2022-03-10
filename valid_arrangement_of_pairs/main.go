package main

type stack []int

func (s *stack) Push(v int) {
	*s = append(*s, v)
}

func (s *stack) Pop() int {
	n := len(*s)
	if n == 0 {
		panic("Stack is empty!")
	}
	v := (*s)[n-1]
	*s = (*s)[:n-1]
	return v
}

func (s *stack) Peek() int {
	n := len(*s)
	if n == 0 {
		panic("Stack is empty!")
	}
	v := (*s)[n-1]
	return v
}

func validArrangement(pairs [][]int) [][]int {
	graph := make(map[int][]int)
	in := make(map[int]int)
	out := make(map[int]int)

	for _, pair := range pairs {
		u := pair[0]
		v := pair[1]
		graph[u] = append(graph[u], v)
		in[v] += 1
		out[u] += 1
	}

	// Indexes of next out going edge to be traversed
	next_edge := make(map[int]int)

	// Find the staring vertex
	start := 0
	for u, _ := range graph {
		start = u
		if in[u]+1 == out[u] {
			break
		}
	}

	// Stack for traversal
	s := make(stack, 0)
	path := make([]int, 0)

	s.Push(start)

	for len(s) > 0 {
		u := s.Peek()
		if next_edge[u] < len(graph[u]) {
			v := graph[u][next_edge[u]]
			s.Push(v)
			next_edge[u] += 1
		} else {
			path = append(path, u)
			s.Pop()
		}
	}

	ans := make([][]int, 0)

	for i := len(path) - 1; i > 0; i-- {
		ans = append(ans, []int{path[i], path[i-1]})
	}

	return ans
}
