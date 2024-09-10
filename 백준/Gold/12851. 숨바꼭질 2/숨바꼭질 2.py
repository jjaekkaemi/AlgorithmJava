import sys
from collections import deque
input = sys.stdin.readline
N, K = map(int, input().split())
result = 0
sum_result = 0
Q = deque()

MAX = 10**5
dist = [0] * (MAX+1)
Q.append(N)

while Q:
    x = Q.popleft()
    count = dist[x]
    if x==K:
        result = count
        sum_result +=1 
        continue

    for nx in (x-1, x+1, x*2):
        if 0<=nx<=MAX :
            if not dist[nx] or dist[nx] == dist[x]+1:
                dist[nx] = count+1
                Q.append(nx)

print(result)
print(sum_result)