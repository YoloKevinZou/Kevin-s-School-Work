

def solve(x,y, dest_x, dest_y, cached = {}):
		
	if x == dest_x and y == dest_y:
		return 1

	if (x,y) in cached:
		return cached[x,y]

	if x < dest_x and y < dest_y:
		cached[x,y]= (solve(x+1, y, dest_x, dest_y) + solve(x, y+1, dest_x, dest_y))
		#return cached[x,y]

	elif x == dest_x:
		cached[x,y] = solve(x, y+1, dest_x, dest_y)
		#return cached[x,y]

	else:
		cached[x,y] = solve(x+1, y, dest_x, dest_y)
		#return cached[x,y]


	return cached[x,y]




def test():
	

	print solve(0,0, 10,15)

test()

