class rules:

	lhs = None
	rhs = None
	prob = 0

	def __init__(self, root, t, xprob):

		lhs = root
		rhs = t
		prob = xprob