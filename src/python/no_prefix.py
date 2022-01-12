from typing import List

# solution for https://www.hackerrank.com/challenges/no-prefix-set/problem

class TrieNode:
    val: chr
    children: map
    is_word: bool

    def __init__(self):
        self.children = {}
        self.is_word = False

class Trie:
    root: TrieNode

    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str) -> bool:
        inserted = False
        n = self.root
        for c in word:
            if c in n.children:
                n = n.children[c]
                if n.is_word:
                    return False
            else:
                new_node = TrieNode()
                new_node.val = c
                n.children[c] = new_node
                n = new_node
                inserted = True
        n.is_word = True
        return inserted


def noPrefix(words):
    t = Trie()
    for w in words:
        if not t.insert(w):
            print("BAD SET")
            print(w)
            return
    print("GOOD SET")


noPrefix(["abcd", "bcd", "abcde", "bcde"])
noPrefix(["ab", "bc", "cd"])
