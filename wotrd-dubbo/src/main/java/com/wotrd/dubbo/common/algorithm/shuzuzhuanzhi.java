package com.wotrd.dubbo.common.algorithm;

public class shuzuzhuanzhi {
    public void toupleTranspose(){
        int[][] num;             //定义一个int类型的2维数组
        num= new int[3][5];
        int[][] num2;             //定义一个int类型的2维数组
        num2= new int[5][3];
        int i,j;
        int temp=0;
        for(i=0;i<3;i++)
        {
            for(j=0;j<5;j++)
            {
                temp=temp+1;
                num[i][j]=temp;
                System.out.print(num[i][j]+"\t");
            }
            System.out.println();
        }
        for(i=0;i<3;i++)
        {
            for(j=0;j<5;j++)
            {
                num2[j][i] = num[i][j];
            }
        }
        System.out.println("-----------分割------------");
        for(i=0;i<5;i++)
        {
            for(j=0;j<3;j++)
            {
                System.out.print(num2[i][j]+"\t");
            }
            System.out.println();
        }

    }
}
