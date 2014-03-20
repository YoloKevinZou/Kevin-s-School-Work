import sys
import math

def skia(a, q):

	cached=[0]*(q+1)

	for i in a:
		cached[i] = cached[i]+1

	result = 0

	for i in range((q+1)/2):		
		result += min(cached[q-i],cached[i])

	half = q/2
	if q%2==0:
		result += cached[half]/2

	return result

def skib(a,q):

	cached = {}

	for i in a:
		
		if i not in cached:
			cached[i]= 1
		else:
			cached[i] += 1

	result = 0

	for i in range((q+1)/2):
		
		if i in cached and (q-i) in cached:
			result += min(cached[i],cached[q-i])


	if q%2==0:
		if q/2 in cached:

			result += (cached[q/2])/2

	return result


def skic(a, q):
	
	a.sort()


	result = 0

	left = 0
	right = len(a)-1

	while left < right:

		if a[left]+a[right] == q:
			result += 1
			left += 1
			right -= 1

		elif a[left]+a[right] > q:
			right -= 1

		elif a[left]+a[right] < q:
			left += 1

	return result


def test():

	n, q = map(int, raw_input().split())
	a = map(int, raw_input().split())

	print skia(a,q)
	print skib(a,q)
	print skic(a,q)
	

test()