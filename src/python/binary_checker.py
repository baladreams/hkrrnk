import queue
from typing import List

# solution for https://www.hackerrank.com/challenges/is-binary-search-tree/problem

class node:
  def __init__(self, data):
      self.data = data
      self.left = None
      self.right = None


def dfs_check(node, cur_max):
    valid = True
    if(node.left != None):
        valid &= dfs_check(node.left, cur_max)
    if(cur_max[0] != None and cur_max[0] >= node.data):
        return False
    cur_max[0] = node.data
    if(valid and node.right != None):
        valid &= dfs_check(node.right, cur_max)
    return valid


def check_binary_search_tree_(root):
    cur_max = [None]
    return dfs_check(root, cur_max)


def listToTree(values: List[int]) -> node:
    root: node = None
    if len(values) > 0:
        root = node(values[0])
        cur_pos = 0
        nodes: queue.Queue[node] = queue.Queue()
        nodes.put(root)
        while cur_pos < len(values)/2:
            cur_node = nodes.get()
            left_pos = 2 * cur_pos + 1
            right_pos = 2 * cur_pos + 2
            if left_pos < len(values) and values[left_pos] != None:
                left_node = node(values[left_pos])
                cur_node.left = left_node
                nodes.put(left_node)
            if right_pos < len(values) and values[right_pos] != None:
                right_node = node(values[right_pos])
                cur_node.right = right_node
                nodes.put(right_node)
            cur_pos += 1
    return root


print(check_binary_search_tree_(listToTree([3, 5, 2, 1, 4, 6, None])))
print(check_binary_search_tree_(listToTree([4, 2, 6, 1, 3, 5, None])))
print(check_binary_search_tree_(listToTree([3, 2, 6, 1, 4, 5, 7])))
