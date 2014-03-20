import random
import sys
sys.setrecursionlimit(5000)

def quickSort(a):

	if len(a) <= 1: 
		return a

	pivot = random.randint(0,len(a)-1)
	left = [x for x in a if x < a[pivot]]
	right = [x for i, x in enumerate(a) if x >= a[pivot] and i!=pivot]

	return quickSort(left) + [a[pivot]] + quickSort(right)

def sort(a):
    if a == []:
        return []
    pivot = a[0]
    left = [x for x in a if x < pivot]
    right = [x for x in a[1:] if x >= pivot]
    return sort(left) + [pivot] + sort(right)


def test():

	a = [4,1,5,3,2,6,3,4]
	print quickSort(a)

	b = [1,3,5]
	print b[0:2]

test()