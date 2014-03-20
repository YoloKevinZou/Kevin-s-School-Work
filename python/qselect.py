import random

def qselect(a,n):

	if a == []:
		return None;
	pivot = random.randint(0,len(a)-1);
	left = [x for x in a if x < a[pivot]]
	right = [x for i, x in enumerate(a) if x>=a[pivot] and i!=pivot]
	
	if len(left)+1 == n:
		return a[pivot]

	elif len(left) >= n:
		return qselect(left,n)

	else: 
		return qselect(right,n-len(left)-1) 

print qselect([5,2,1,23,5,2],3)