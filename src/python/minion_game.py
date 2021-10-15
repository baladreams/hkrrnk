# solution for https://www.hackerrank.com/challenges/the-minion-game/problem

def minion_game(string):
    vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'}
    kevin, stuart = 0, 0
    for i in range(len(string)):
        if(string[i] in vowels):
            kevin += len(string) - i
        else:
            stuart += len(string) - i
    if kevin == stuart:
        print("Draw")
    elif kevin > stuart:
        print("Kevin {}".format(kevin))
    else:
        print("Stuart {}".format(stuart))


minion_game("BANANA")
