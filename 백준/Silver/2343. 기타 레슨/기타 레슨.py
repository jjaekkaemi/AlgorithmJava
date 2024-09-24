import sys
input = sys.stdin.readline

N, M = map(int, input().split())
video = list(map(int, input().split()))
answer = 0
left, right = max(video), sum(video)

while left<=right:
    mid = (left+right)//2
    count, video_sum = 0,0
    for v in video:

        if video_sum+v>mid:
            count+=1
            video_sum = 0
        video_sum += v
    if video_sum :
        count+=1

    if count> M:
        left = mid+1
    else:
        right = mid-1
        answer = mid
print(answer)