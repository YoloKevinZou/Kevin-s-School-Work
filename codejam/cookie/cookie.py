import sys
sys.setrecursionlimit(1000000)

def solve(rate, time, x, c, f):
	
	while True:

		currentTime = x/rate
		nextRate = rate + f
		endTime = (c/rate) + (x/nextRate)

		if currentTime < endTime:
			time += currentTime
			return time

		else: 
			
			time += c/rate
			rate = nextRate


def test():
	k = input()

	for q in range(k):

		c,f,x = map(float, raw_input().split())

		r = 2

		result = solve(r, 0, x, c, f)

		print "Case #" + str(q+1) + ": %.7f" % result 

test()