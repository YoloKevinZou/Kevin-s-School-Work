class node:

	data = None
	parent = None
	children = None
	is_variable = False

	def __init__(self, s):
		self.data = s
		self.parent = None
		self.children = []

	def add_child(self, node):
		self.children.append(node)

