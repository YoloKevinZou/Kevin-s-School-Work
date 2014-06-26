#!/usr/bin/env python

import re, sys
from operator import add

RULE_PATTERN = re.compile('^(.*) -> (.*) ### prob=(.*)$')

def stringify(tokens, glue = ' '):
    """
    Given a list of strings, joins the strings into a single string using the
    glue specified, removing any quotes at the ends of each of the tokens.

    >>> stringify(["sunny", "day"])
    'sunny day'
    """
    return glue.join(token.strip('"') for token in tokens)

def parse_rule(rule):
    m = RULE_PATTERN.match(rule)

    if not m: raise Error('Incorrectly formatted rule')

    lhs, rhs, p = m.groups()

    return Tree.from_string(lhs), rhs.split(), float(p)

def translate(tree, rules, cache = {}):

    if isinstance(tree, str): return 1.0, tree

    if tree in cache: return cache[tree]

    best = 0
    string = None

    for lhs, rhs, rule_prob in rules:
        translation = []

        matched, sublist = tree.match(lhs, rhs)

        if matched:
            prob = rule_prob

            for n in sublist:
                pi, si = translate(n, rules, cache)
                prob = prob * pi

                translation.append(si)

            if prob > best:
                best = prob
                string = stringify(translation)

    cache[tree] = best, string
    return cache[tree]

class Tree():

    def __init__(self, structure = '', text = None, is_free = False):
        self.text = text
        self.is_free = is_free
        self.structure = structure

        self.children = []

    def __repr__(self):
        if self.is_free:
            return "%s:%s" % (self.text, self.structure)
        else:
            # Show the text if we have text; otherwise just show the children.
            if self.text:
                s = '"%s"' % self.text
            else:
                s = ' '.join(str(c) for c in self.children)

            return '%s(%s)' % (self.structure, s) if self.structure else s

    def leaves(self):
        "Returns a list consisting of the text within the leaves of the tree."
        if len(self.children) == 0:
            return [self.text]

        return reduce(add, map(lambda x: x.leaves(), self.children))

    def match(self, lhs, rhs):
        """
        """
        m, subs = self.__match(lhs)

        if not m: return False, None

        return True, map(lambda x: subs[x] if x in subs else x, rhs)

    def __match(self, lhs, subs = {}):
        if self.structure != lhs.structure or not lhs.is_free and (self.text != lhs.text or len(self.children) != len(lhs.children)):
            return False, None

        if lhs.is_free:
            subs[lhs.text] = self
        else:
            for i, child in enumerate(self.children):
                t, _ = child.__match(lhs.children[i], subs)

                if not t: return False, None

        return True, subs

    @staticmethod
    def from_string(s):
        """
        """
        tree = Tree.__from_string(s)

        assert len(tree.children) == 1

        return tree.children[0]

    @staticmethod
    def __from_string(s):

        # Base case and recursive case return a tree, so create one for both.
        tree = Tree()

        # Base case: If s is text inside quotes.
        if s[0] == s[-1] == '"':
            tree.text = s.strip('"')
            return tree

        for structure, text, is_free in tokens(s):
            if is_free:
                subtree = Tree(structure, text, is_free)
            else:
                subtree = Tree.__from_string(text)
                subtree.structure = structure

            tree.children.append(subtree)

        return tree

def tokens(s):
    """
    """
    N = len(s)
    cursor = 0

    while cursor < N:

        if s[cursor] != ' ':
            paren_index = s.find('(', cursor)
            colon_index = s.find(':', cursor)

            # We've found a letter, so there must be a ( or : in s[cursor:].
            assert paren_index != -1 or colon_index != -1

            # It's a variable. Notice that we also have to check that
            # colon_index != cursor because that means that the token looks like
            # :(":"), which we don't want to treat as a variable (this error
            # occurred in the third example IO).
            if colon_index != -1 and (paren_index == -1 or colon_index < paren_index) and colon_index != cursor:
                space_index = colon_index + 1

                while space_index < N and s[space_index] != ' ':
                    space_index += 1

                structure = s[colon_index + 1 : space_index]
                text = s[cursor : colon_index]
                free = True

                cursor = space_index

            # It's a parenthesis.
            else:
                count = 0
                close_index = paren_index + 1

                while close_index < N and (count > 0 or s[close_index] != ')'):

                    if s[close_index] == '(':
                        count += 1
                    if s[close_index] == ')':
                        count -= 1

                    close_index += 1

                structure = s[cursor : paren_index]
                text = s[paren_index + 1 : close_index]
                free = False

                cursor = close_index

            yield (structure, text, free)

        cursor += 1

def main():
    # Read in the rules from the file specified in the command line.
    if len(sys.argv) == 1:
        print 'Usage: %s <rule_file> [-d|-k <K>]' % sys.argv[0]
        sys.exit(-1)

    # Read in the rule file, specified in sys.argv[1]
    with open(sys.argv[1]) as f:
        rules = [parse_rule(line.strip()) for line in f]

    #
    # Process additional options from the command line here.
    #

    # Read in the input from stdin.
    for line in sys.stdin:
        tree = Tree.from_string(line.strip())

        prob, out_text = translate(tree, rules)
        in_text = stringify(tree.leaves())

        if out_text:
            print '%s -> %s ### prob=%.03f' % (in_text, out_text, prob)
        else:
            print '%s -> *** failed ***' % in_text

if __name__ == '__main__':
    main()