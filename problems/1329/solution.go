package main

import "sort"

type Diagon struct {
	mat    [][]int
	m      int
	n      int
	x0     int
	y0     int
	length int
}

func NewDiagon(mat [][]int, x0, y0 int) *Diagon {
	n := len(mat)
	m := len(mat[0])
	length := n - x0
	if m-y0 < length {
		length = m - y0
	}
	return &Diagon{mat, m, n, x0, y0, length}
}

func (d *Diagon) Len() int {
	return d.length
}

func (d *Diagon) Less(i, j int) bool {
	xi := d.x0 + i
	yi := d.y0 + i
	xj := d.x0 + j
	yj := d.y0 + j
	return d.mat[xi][yi] < d.mat[xj][yj]
}

func (d *Diagon) Swap(i, j int) {
	xi := d.x0 + i
	yi := d.y0 + i
	xj := d.x0 + j
	yj := d.y0 + j
	d.mat[xi][yi], d.mat[xj][yj] = d.mat[xj][yj], d.mat[xi][yi]
}

func diagonalSort(mat [][]int) [][]int {
	n := len(mat)
	m := len(mat[0])
	x0 := 0
	for y0 := 0; y0 < m; y0++ {
		d := NewDiagon(mat, x0, y0)
		sort.Sort(d)
	}

	y0 := 0
	for x0 := 1; x0 < n; x0++ {
		d := NewDiagon(mat, x0, y0)
		sort.Sort(d)
	}

	return mat
}
