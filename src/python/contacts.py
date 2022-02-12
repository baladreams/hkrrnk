import math
import os
import random
import re
import sys

# solution for https://www.hackerrank.com/challenges/contacts/problem

class TrieNode:
    val: chr
    children: map
    is_word: bool
    count: int

    def __init__(self):
        self.children = {}
        self.is_word = False
        self.count = 1


class Trie:
    root: TrieNode

    def __init__(self):
        self.root = TrieNode()
        self.root.count = 0

    def insert(self, word: str):
        n = self.root
        for c in word:
            if c in n.children:
                n = n.children[c]
                n.count += 1
            else:
                new_node = TrieNode()
                new_node.val = c
                n.children[c] = new_node
                n = new_node

    def find(self, prefix: str):
        n = self.root
        for c in prefix:
            if c in n.children:
                n = n.children[c]
            else:
                return 0
        return n.count


def contacts(queries):
    # Write your code here
    trie = Trie()
    results = []
    for query in queries:
        op = query[0]
        value = query[1]
        if op == "add":
            trie.insert(value)
        if op == "find":
            result = trie.find(value)
            results.append(result)
    return results


print(contacts([["add", "ed"], ["add", "eddie"], ["add", "edward"], [
      "find", "ed"], ["add", "edwina"], ["find", "edw"], ["find", "a"]]))
print(contacts([["add", "hack"], ["add", "hackerrank"],
      ["find", "hac"], ["find", "hak"]]))
