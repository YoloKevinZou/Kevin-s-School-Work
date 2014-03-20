import random
import time
import sys
from heapq import *
sys.setrecursionlimit(5000)
from operator import itemgetter


def nbesta(a,b):

	#put the elements into c as a tuple
	c=[(x,y,x+y) for x in a for y in b]

	#sort the elements by the 3rd and 2nd value of each tuple
	c = sorted(c, key=itemgetter(2,1))[:len(a)]

	#slice out the last value in each tuple
	d = [(i[0],i[1]) for i in c]

	return d

''' 
Complexity of nBestA
n^2 to put element into c
nlogn to sort c
nlogn to rearrange c
Complexity: n^2 + nlogn + nlogn 
Result: O(n^2)
'''


def nbestb(a,b):

	#put the elements into c as a tuple
	c = [(x,y,x+y) for x in a for y in b]

	#get the nth smallest using qselect
	d = qselect(c, len(a))[:len(a)]
	
	d = sorted(d, key=itemgetter(2,1))
	e = [(i[0],i[1]) for i in d]

	return e

'''
Complexity of nbestb
n^2 to put elements into c
n^2 to qselect the nth smallest from c
nlogn to sort d
nlogn to rearrange d
Complexity: n^2 + n^2 + nlogn + nlogn
Result: O(n^2)
'''

def qselect(a,n):
	
	if a == []:
		return None;

	#choose a random pivot
	pivot = random.randint(0,len(a)-1)

	#put everything less then the pivot on the left
	left = [x for x in a if x[2] < a[pivot][2]]

	#put everything greater or equal to the pivot on the right
	#but excluding itself using index
	right = [x for i, x in enumerate(a) if x[2]>=a[pivot][2] and i!=pivot]

	#if length of left + 1 is n then all element on left and the pivot is the nth smallest
	if len(left)+1 == n:
		return left + [a[pivot]]

	elif len(left) >= n:
		return qselect(left,n)

	else: 
		return left + [a[pivot]] + qselect(right,n-len(left)-1) 

def nbestc(x, y):

	x.sort()
	y.sort()

	result = []
	temp = []
	cached={}

	#push first element into heap
	heappush(temp, (x[0]+y[0],y[0],x[0],0,0))
	cached.update({(0,0):"USED"})
 
	#loop until the result array = n
	while len(result) != len(x):
		
		#current index x and y for root of the heap
		currentX = temp[0][3]
		currentY = temp[0][4]

		#the right and down value from the element
		right = currentX + 1
		down = currentY + 1

		#pop the heap and add it into the list
		result.append(heappop(temp))

		#if there is element at the right and the index is not in cache
		#insert the element into heap
		if currentX != len(x)-1 and (right,currentY) not in cached:
			heappush(temp, (x[right]+y[currentY], y[currentY], x[right], right, currentY))
			cached.update({(right,currentY):"USED"})
		
		#if there is element at the bottom and the index is not in cache
		#insert the element into heap
		if currentY != len(x)-1 and (currentX,down) not in cached:
			heappush(temp, (x[currentX] + y[down], y[down], x[currentX], currentX, down))
			cached.update({(currentX,down):"USED"})

	#rearrange the elements into correct output
	result = [(x[2],x[1]) for x in result]

	return result

'''
Complexity for nbestc

n --- iterate until the result list has nth smallest
2logn --- can push 2 items into the heap for each iteration
Complexity: n*2logn
Result: 2nlogn
'''

def test():

	x, y = [4,1,5,3],[2,6,3,4]
    
	n = 5000

	a = [random.randint(0,100) for _ in xrange(n)]
	b = [random.randint(0,100) for _ in xrange(n)]
	
	t = time.time()
	nbesta(a,b)
	print 'Slowest: ' + str(time.time() - t) 
	
	t = time.time()
	nbestb(a,b)
	print 'Slow: ' + str(time.time() - t) 

	t = time.time()
	nbestc(a,b)
	print 'fast: ' + str(time.time() - t) 
	
test()


