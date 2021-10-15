from typing import OrderedDict

# solution for https://www.hackerrank.com/challenges/word-order/problem

n = int(input())
words = [input() for _ in range(n)]
word_counts = OrderedDict()
for word in words:
    word_counts[word] = word_counts[word] + 1 if word in word_counts else 1
print(len(word_counts))
print(*word_counts.values())
