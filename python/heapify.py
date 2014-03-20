import math
import sys
sys.setrecursionlimit(5000)

def parent(i):
	return int(math.floor((i/2)))

def left(i):
	return 2*i

def right(i):
	return 2*i+1

def bubbleDown(a, i):

	l = left(i)
	r = right(i)

	if l <=len(a)-1 and a[i] < a[l]:
		largest = l
	else: largest = i
	
	if r <=len(a)-1 and a[largest] < a[r]:
		largest = r

	if largest!=i:
		temp = a[i]
		a[i] = a[largest]
		a[largest] = temp
		bubbleDown(a,largest)
	

def buildHeap(a):

	for i in range(1,int(math.floor(len(a)/2))):
		bubbleDown(a,i)

def test():

	k = input("")

	x = [None]*(k+1)

	heapIndex = 1

	for insertIndex in range(1,k+1):

		x[insertIndex] = int(input(""))
		insertIndex = insertIndex + 1
	
	#build the heap of size k
	buildHeap(x)

	for line in sys.stdin:

		if int(line) < x[1]:

			x[1] = int(line)
			bubbleDown(x,1)

	z = x[1:]
	z.sort()
	print z

test()