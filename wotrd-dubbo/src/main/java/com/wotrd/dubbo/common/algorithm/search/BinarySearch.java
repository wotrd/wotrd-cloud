package com.wotrd.dubbo.common.algorithm.search;

/**
 * 二分查找针对有序数组、
 */
public class BinarySearch {

    public void test() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};  //0下标放的是数组长度
        System.out.println(binarySearch(arr, 3));
    }

    public int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] > key) high = mid - 1;
            else if (arr[mid] < key) low = mid + 1;
            else return mid;
        }
        return -1;
    }

}
