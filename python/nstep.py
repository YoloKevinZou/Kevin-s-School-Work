import sys
sys.setrecursionlimit(500)

def solve(n, cached={}):
	
	if n < 0:
		return 0

	if n == 0:
		return 1

	if n in cached:
		return cached[n]

	cached[n] = (solve(n-1) + solve(n-2) + solve(n-3))

	return cached[n]

def test():

	n = 4

	print solve(n)

test()	