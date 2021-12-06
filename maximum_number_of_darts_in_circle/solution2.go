package main

import (
	"math"
	"sort"
)

type Event struct {
	radius float64
	enter  bool
}

type Events []Event

func (s Events) Len() int {
	return len(s)
}

func (s Events) Swap(i, j int) {
	s[i], s[j] = s[j], s[i]
}

func (s Events) Less(i, j int) bool {
	if s[i].radius == s[j].radius {
		return s[i].enter
	} else {
		return s[i].radius < s[j].radius
	}
}

func PhaseRadius(point []int) float64 {
	x := point[0]
	y := point[1]
	if x == 0 {
		if y > 0 {
			return math.Pi / 2
		} else {
			return 3 * math.Pi / 2
		}
	}

	atan := math.Atan(float64(y) / float64(x))

	if x > 0 {
		if y >= 0 {
			return atan
		} else {
			return atan + 2*math.Pi
		}
	} else {
		return atan + math.Pi
	}
}

func numPoints(points [][]int, r int) int {
	D := 2 * r
	D2 := D * D
	ans := 0

	for i, p := range points {
		ansp := 0
		events := make(Events, 0)
		for j, q := range points {
			pq := []int{q[0] - p[0], q[1] - p[1]}
			d2 := pq[0]*pq[0] + pq[1]*pq[1]
			if i != j && d2 <= D2 {
				A := PhaseRadius(pq)
				B := math.Acos(math.Sqrt(float64(d2)) / float64(D))
				events = append(events, Event{A - B, true})
				events = append(events, Event{A + B, false})
			}
		}

		sort.Sort(events)

		for _, event := range events {
			if event.enter {
				ansp += 1
				if ansp > ans {
					ans = ansp
				}
			} else {
				ansp -= 1
			}
		}
	}

	return ans
}
