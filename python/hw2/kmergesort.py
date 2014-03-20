from heapq import *
import time
import random
import sys
sys.setrecursionlimit(5000)

def mergeSort(a):
	
	sortedList = []

	temp = a

	heap = []
	for i in range(len(temp)):
		heappush(heap,(temp[i][0],i))

	while len(heap) != 0:
		
		index = heap[0][1]
		element = heap[0][0]
		heappop(heap)
		sortedList.append(element)
		heappop(temp[index])

		if len(temp[index])!=0:
			heappush(heap, (temp[index][0], index))

	return sortedList

def split(a,k):

	if len(a) == 1:
		return a

	temp = []

	for i in range(k):
		
		c = a[i::k]

		if len(c) > 0:
			c = split(c,k)
			temp.append(c)

	return mergeSort(temp)

'''
Complexity for split + mergeSort
mergesort will have a maximum of log(k) splits 
and at each level their are a maximum of n splits
therefore the complexity is O(nlog(K))
'''


def test():

	a = [random.randint(0,100) for _ in xrange(5000)]

	n = 5

	t = time.time()
	split(a,n)
	print 'SPLIT 2: ' + str(time.time() - t) 

	t = time.time()
	split(a, 10)
	print 'SPLIT 3: ' + str(time.time() - t)

test()