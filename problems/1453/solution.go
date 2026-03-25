package main

import (
	"math"
)

type Point struct {
	x int
	y int
}

func NewPoint(coordinates []int) *Point {
	return &Point{coordinates[0], coordinates[1]}
}

func GetCenters(r int, p1, p2 *Point) [][]float64 {
	xa := float64(p2.x-p1.x) / 2
	ya := float64(p2.y-p1.y) / 2
	x0 := float64(p1.x) + xa
	y0 := float64(p1.y) + ya
	a2 := xa*xa + ya*ya
	b2 := float64(r*r) - a2

	if b2 < 0 {
		return [][]float64{}
	}

	if b2 == 0 {
		return [][]float64{{float64(x0), float64(y0)}}
	}

	x3 := float64(x0) - math.Sqrt(float64(b2*ya*ya/a2))
	y3 := float64(y0) + math.Sqrt(float64(b2*xa*xa/a2))
	x4 := float64(x0) + math.Sqrt(float64(b2*ya*ya/a2))
	y4 := float64(y0) - math.Sqrt(float64(b2*xa*xa/a2))

	return [][]float64{{x3, y3}, {x4, y4}}
}

func numPoints(points [][]int, r int) int {
	n := len(points)
	ans := 1
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			p1 := NewPoint(points[i])
			p2 := NewPoint(points[j])
			centers := GetCenters(r, p1, p2)
			if len(centers) != 0 {
				for _, center := range centers {
					within := 0
					for k := 0; k < n; k++ {
						xk := float64(points[k][0]) - center[0]
						yk := float64(points[k][1]) - center[1]
						dist := math.Sqrt(xk*xk+yk*yk) - float64(r)
						if math.Abs(dist) < 1e-6 || dist < 0 {
							within += 1
						}
					}

					if within > ans {
						ans = within
					}
				}
			}
		}
	}

	return ans
}
