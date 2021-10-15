# solution for https://www.hackerrank.com/challenges/no-idea/problem

n, m = map(int, input().split())
numbers = list(map(int, input().split()))
a = set(map(int, input().split()))
b = set(map(int, input().split()))
positives = sum(map(lambda x: 1 if x in a else 0,numbers))
negatives = sum(map(lambda x: 1 if x in b else 0,numbers))
print(positives - negatives)
