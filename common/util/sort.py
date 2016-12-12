#coding:utf-8

import time

#冒泡排序
def bubble_sort(list):
    if list:
        pass
    count = len(list)
    for i in range(0, count-1):
        for j in range(0, count-1-i):
            if list[j] > list[j+1]:
                list[j], list[j+1] = list[j+1], list[j]
    return list

#插入排序
def insert_sort(list):
    if list:
        pass
    count = len(list)
    for i in range(0, count):
        key = list[i]
        j = i - 1
        while j>=0:
            if list[j] > key:
                list[j+1],list[j] = list[j],key
            j -=1
    return list

#选择排序
def select_sort(list):
    if list:
        pass
    count = len(list)
    for i in range(0, count):
        min = i
        for j in range(i+1, count):
            if list[min] > list[j]:
                list[j],list[min] = list[min],list[j]
    return list

# 快速排序
def quick_sort(lists, left, right):
    if left >= right:
        return lists
    key = lists[left]
    low = left
    high = right
    while left < right:
        while left < right and lists[right] >= key:
            right -= 1
        lists[left] = lists[right]
        while left < right and lists[left] <= key:
            left += 1
        lists[right] = lists[left]
    lists[right] = key
    quick_sort(lists, low, left - 1)
    quick_sort(lists, left + 1, high)
    return lists

#归并排序
def merge_sort(lists):
    if len(lists) <= 1:
        return lists
    num = len(lists)/2
    left = merge_sort(lists[:num])
    right = merge_sort(lists[num:])
    return merge(left,right)

def merge(left, right):
    i,j = 0,0
    result = []
    while i<len(left) and j< len(right):
        if left[i] <= right[j]:
            result.append(left[i])
            i += 1
        else:
            result.append(right[j])
            j += 1
    result += left[i:]
    result += right[j:]
    return   result

# 调整满足堆的性质（大堆和小堆）
def adjust_heap(lists, i, size):
    lchild = 2 * i + 1
    rchild = 2 * i + 2
    max = i
    if i < size / 2:
        if lchild < size and lists[lchild] > lists[max]:
            max = lchild
        if rchild < size and lists[rchild] > lists[max]:
            max = rchild
        if max != i:
            lists[max], lists[i] = lists[i], lists[max]
            adjust_heap(lists, max, size)


def build_heap(lists, size):
    for i in range(0, (size / 2))[::-1]:
        adjust_heap(lists, i, size)


# 堆排序
def heap_sort(lists):
    size = len(lists)
    build_heap(lists, size)
    for i in range(0, size)[::-1]:
        lists[0], lists[i] = lists[i], lists[0]
        adjust_heap(lists, 0, i)
    return lists



if __name__ == '__main__':
    list = [8,1,2,6,4,5,9,0]
    start = time.clock()
    print bubble_sort(list)
    print insert_sort(list)
    print select_sort(list)
    print quick_sort(list,0,len(list)-1)
    print  merge_sort(list)
    print  heap_sort(list)
    end = time.clock()
    print "sort: %f s" % (end - start)



