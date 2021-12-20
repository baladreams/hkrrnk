# https://www.hackerrank.com/challenges/merging-communities/problem

class CustomInput:

    input_number = 0

    def custom_input():
        inputs = ["3 6",
                  "Q 1",
                  "M 1 2",
                  "Q 2",
                  "M 2 3 ",
                  "Q 3 ",
                  "Q 2"]
        ret_val = inputs[CustomInput.input_number]
        CustomInput.input_number += 1
        return ret_val

    # def custom_input():
    #     return input()


counts = CustomInput.custom_input().split(" ")
parent_map = {}
child_map = {}
for i in range(0, int(counts[0])):
    parent_map[i+1] = i+1
    child_map[i+1] = [i+1]
for i in range(0, int(counts[1])):
    inputs = CustomInput.custom_input().split(" ")
    if inputs[0] == "M":
        n1 = int(inputs[1])
        n2 = int(inputs[2])
        while n1 in parent_map and n1 != parent_map[n1]:
            n1 = parent_map[n1]
        while n2 in parent_map and n2 != parent_map[n2]:
            n2 = parent_map[n2]
        if(n1 > n2):
            temp = n1
            n1 = n2
            n2 = temp
        if n1 != n2:
            parent_map[n2] = n1
            child_map[n1].extend(child_map[n2])
            child_map[n2] = []
    elif inputs[0] == "Q":
        n = int(inputs[1])
        while n != parent_map[n]:
            n = parent_map[n]
        print(len(child_map[n]))
