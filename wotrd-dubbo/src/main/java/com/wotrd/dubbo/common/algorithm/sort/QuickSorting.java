package com.wotrd.dubbo.common.algorithm.sort;

import java.util.Arrays;

/**
 * 选择一个关键值作为基准值。不稳定
 * 比基准值小的都在左边序列（一般是无序的），比基准值大的都在右边（一般是无序的）。一般选择序列的第一个元素。
 */
public class QuickSorting {
    public void test() {
        int[] arr = {7, 3, 5, 1, 2, 8, 9, 2, 6};
        sort(arr,0,arr.length-1);
        System.out.println("jieguo="+ Arrays.toString(arr));
    }
    public void sort(int [] arr,int low,int high){
        if (low<high){
            int index = partition(arr,low,high);
            sort(arr,0,index-1);
            sort(arr,index+1,high);
        }
    }
    public int partition(int [] arr,int low, int high){
        if (null == arr || arr.length<=0 || low<0 ||  high >= arr.length) return 0;
        int prio= arr[(high-low)/2+low];
        while(arr[low]<prio)
            low++;
        while (arr[high]>prio)
            high--;
        if (low <= high) {
            swap(arr,low,high);//最终将基准数归位
            low++;
            high--;
        }
        return low;
    }
    public void swap(int [] arr,int low ,int high){
        int temp=arr[low];
        arr[low]=arr[high];
        arr[high]=temp;
    }
}
