package com.wotrd.dubbo.common.algorithm.sort;

/**
 * 排序算法
 */
public class Souting {
    /**
     * 冒泡排序，每次便利都将相邻的两个数进行交换排序。知道全部拍完。
     */
    public void bubbleSorting(){
        int [] arr={1,4,2,5,3,8,7,9,6,10,11,7};
        for (int i=0;i<arr.length-1;i++)
        {
            for (int j=0;j<arr.length-i-1;j++){
                if (arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        showArr(arr);
    }
    /**
     * 选择排序每次都是选择最小值放在最前面。
     */
    public void selectSort(){
        int [] arr={1,4,2,5,3,8,7,9,6,10,11,7};
        for (int i=0;i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                if (arr[i]>arr[j]){
                    int temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                }
            }
        }
        showArr(arr);
    }
    /**
     * 插入排序，将一组数据分成两组，我分别将其称为有序组与待插入组。每次从待插入组中取出一个元素，
     * 与有序组的元素进行比较，并找到合适的位置，将该元素插到有序组当中。就这样，每次插入一个元素，
     * 有序组增加，待插入组减少。
     */
    public void insertSorting(){
        int [] arr={1,4,2,5,3,8,7,9,6,10,11,7};
        for (int i=0;i<arr.length-1;i++){
            int prio=arr[i+1];
            int index=i;
            while (index>0 && arr[index]>prio){
                int temp = arr[index];
                arr[index]= prio;
                arr[index+1] = temp;
                index --;
            }
        }
        showArr(arr);
    }
    /**
     * 希尔排序，比如现在有数组{82 ,31 ,29 ,71, 72, 42, 64, 5,110} 第一次取增量设置为array.length/2 = 4
     * 然先从82开始以4为增量遍历直到末尾，得到（82,42） 排序得到{42 ,31 ,29 ,71, 72, 82, 64, 5,110}。
     * 后从第二个数31开始重复上一个步骤，得到（31,64） 排序得到{42 ,31 ,29 ,71, 72, 82, 64, 5,110}...
     * 以4为增量的遍历完数组之后，得到的结果是{42 ,31,5,71,72,82,64,29,110}
     */
    public void shellSorting(){
        int [] arr={82 ,31 ,29 ,71, 72, 42, 64, 5,110};
        int increment= arr.length/2;
        while (increment>=1){
            for (int i=0;i<arr.length;i++){
                for (int j=i;j<arr.length-increment;j=j+increment){
                    if (arr[j]>arr[j+increment]){
                        int temp = arr[j+increment];
                        arr[j+increment]=arr[j];
                        arr[j]=temp;
                    }
                }
            }
            increment/=2;
        }
        showArr(arr);
    }
    private void showArr(int[] arr) {
        for (int a:arr){
            System.out.print(String.valueOf(a)+"-");
        }
    }
}
