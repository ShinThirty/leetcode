package main

type Queue []int

func (q *Queue) Put(i int) {
	*q = append(*q, i)
}

func (q *Queue) Get() int {
	old := *q
	n := len(old)
	item := old[0]
	old[0] = 0
	*q = old[1:n]
	return item
}

func (q *Queue) Len() int {
	return len(*q)
}

func (q *Queue) IsEmpty() bool {
	return q.Len() == 0
}

func networkBecomesIdle(e [][]int, p []int) int {
	n := len(p)
	s := make([]int, n)

	g := make(map[int][]int)

	for _, e := range e {
		u := e[0]
		v := e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}

	q := make(Queue, 1)
	q[0] = 0

	black := make(map[int]struct{})
	black[0] = struct{}{}
	d := 0

	for !q.IsEmpty() {
		l := q.Len()
		for i := 0; i < l; i++ {
			u := q.Get()
			s[u] = d
			for _, v := range g[u] {
				_, ok := black[v]
				if !ok {
					q.Put(v)
					black[v] = struct{}{}
				}
			}
		}
		d += 1
	}

	t := 0
	for i, pt := range p {
		if i != 0 {
			extra := (2*s[i] - 1) / pt
			alt := extra*pt + 2*s[i]
			if alt > t {
				t = alt
			}
		}
	}

	return t + 1
}
