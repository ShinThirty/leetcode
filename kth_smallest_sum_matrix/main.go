package main

import "container/heap"

type Sum struct {
	val           int
	indexesofRows []int
	index         int
}

func NewSum(cur *Sum) *Sum {
	var sum Sum
	sum.val = cur.val
	sum.indexesofRows = make([]int, len(cur.indexesofRows))
	copy(sum.indexesofRows, cur.indexesofRows)
	return &sum
}

func NextSums(cur *Sum, mat [][]int) []*Sum {
	m := len(mat[0])
	nextSums := make([]*Sum, 0)

	for r, c := range cur.indexesofRows {
		if c < m-1 {
			sum := NewSum(cur)
			sum.indexesofRows[r] += 1
			sum.val = sum.val + mat[r][c+1] - mat[r][c]
			nextSums = append(nextSums, sum)
		}
	}

	return nextSums
}

type PriorityQueue []*Sum

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(i, j int) bool {
	return pq[i].val < pq[j].val
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
	pq[i].index = i
	pq[j].index = j
}

func (pq *PriorityQueue) Push(x interface{}) {
	n := len(*pq)
	item := x.(*Sum)
	item.index = n
	*pq = append(*pq, item)
}

func (pq *PriorityQueue) Pop() interface{} {
	old := *pq
	n := len(old)
	item := old[n-1]
	old[n-1] = nil
	item.index = -1
	*pq = old[0 : n-1]
	return item
}

func (pq *PriorityQueue) Put(x *Sum) {
	heap.Push(pq, x)
}

func (pq *PriorityQueue) Get() *Sum {
	return heap.Pop(pq).(*Sum)
}

func kthSmallest(mat [][]int, k int) int {
	n := len(mat)
	pq := make(PriorityQueue, 0)

	var init Sum
	init.val = 0
	for i := 0; i < n; i++ {
		init.val += mat[i][0]
	}
	init.indexesofRows = make([]int, n)
	pq.Put(&init)
	visited := make(map[[40]int]struct{})

	var ans int
	for i := 0; i < k; i++ {
		cur := pq.Get()
		ans = cur.val
		nextSums := NextSums(cur, mat)
		for _, sum := range nextSums {
			var key [40]int
			for i, val := range sum.indexesofRows {
				key[i] = val
			}

			if _, exist := visited[key]; !exist {
				pq.Put(sum)
				visited[key] = struct{}{}
			}
		}
	}

	return ans
}
