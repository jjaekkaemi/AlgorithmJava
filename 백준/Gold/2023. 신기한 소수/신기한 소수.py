import sys, math
sys.setrecursionlimit(10000)
input = sys.stdin.readline

N = int(input())

def is_prime(num):
    for i in range(2, int(math.sqrt(num))+1):
        if num%i == 0:
            return False
    return True

def dsf(num, digit):
    if not is_prime(num):
        return
    if 10**(N-1)<=digit:
        print(num)
        return
    for i in [1,3,5,7,9]:
        dsf(num*10+i, digit*10)
    
for i in [2,3,5,7]:
    dsf(i, 1)