from heapq import *
import random
from operator import itemgetter

def closest_unsorted(a, x, k):

	temp = [(abs(x-j), j, i) for i, j in enumerate(a)]

	temp = sorted(temp, key=itemgetter(0))
	result = []
	for i in range(k):
		result.append(temp[i])

	result = sorted(result, key=itemgetter(2))
	result = [x[1] for x in result]
	
	return result



def test():
	a = [4,1,3,2,7,4]

	print closest_unsorted(a, 5.2, 2)
	print closest_unsorted(a, 6.5, 3)

test()