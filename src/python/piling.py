# solution for https://www.hackerrank.com/challenges/piling-up/problem

n = 2  # int(input())
input_strings = ["4 3 2 1 3 4", "1 3 2"]
for i in range(n):
    #input()
    input_string = input_strings[i]  # input()
    blocks = list(map(int, input_string.split()))
    changes = 0
    for i in range(1, len(blocks)-1):
        if(blocks[i] < blocks[i-1] and blocks[i] < blocks[i+1]):
            changes += 1
        elif (blocks[i] > blocks[i-1] and blocks[i] > blocks[i+1]):
            changes += 2
    if(changes > 1):
        print("No")
    else:
        print("Yes")
