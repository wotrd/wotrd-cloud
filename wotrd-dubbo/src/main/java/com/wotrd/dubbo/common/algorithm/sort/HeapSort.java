package com.wotrd.dubbo.common.algorithm.sort;


import java.util.Arrays;

/**
 * 堆排序是一种树形选择排序方法
 * 它是完全二叉树。即除了树的最后一层节点不需要是满的，其他的每层从左到右完全是满的。
 * 它常常是数组实现的。
 * 堆中的每个节点都必须满足堆的条件。
 * 每个节点的关键字都不大于其子节点的关键字，这种堆称为小根堆。
 * 每个节点的关键字都不小于其子节点的关键字，这种堆称为大堆根
 * 2、堆与二叉搜索树的不同：
 * 堆和二叉搜索树相比是弱序的。在二叉搜索树中，所有节点的关键字大于其左子树的关键字，
 * 小于其右子树的关键字。但是在堆中，每个节点都不小于其子节点，即从 根到叶子节点的每条路径都是降序排列的。
 */
public class HeapSort {
    public void test(){
        int[] arr = {7,23,45,9,40,73,12,49};  //0下标放的是数组长度，
        heapSort(arr);
        System.out.println("--"+Arrays.toString(arr));
    }
    void heapAdjust(int[] arr,int s,int m){
        //已知arr[s...m]中记录的关键字除arr[s]之外均满足堆的定义，本函数调整arr[s]的关键字，使arr[s...m]成为一个最大堆
        int rc = arr[s]; //s是最后一个非叶子节点
        for(int j=2*s;j <= m;j = s*2){
            if(j<m && arr[j]<arr[j+1])
                j++;  //j为key较大的下标
            if(rc >= arr[j]) break;
            arr[s] = arr[j];  //上移到父节点
            s=j;
        }
        arr[s]=rc;  //要放入的位置
    }
    void heapSort(int[] arr){
        //对数组进行建堆操作，就是从最后一个非叶结点进行筛选的过程
        for(int i=(arr.length-1)/2;i > 0;i--){//因为0没有使用，所以length-1
            heapAdjust(arr,i,arr.length-1);
        }
        System.out.println("........建堆完成.............");
        for(int i=arr.length-1; i>1; i--){
            int temp = arr[1];
            arr[1] = arr[i];
            arr[i] = temp;
            heapAdjust(arr,1,i-1);
        }
    }
}
