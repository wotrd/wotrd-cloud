package com.wotrd.dubbo.common.algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuihuaSolution {

    public static void main(String[] args) {
        int[] nums = {0, 0, 1};
//        int rob = rob(nums);
        log.info("rob:{}", moveZeroes(nums));

//        nums = [0,1,0,3,12]
//        输出: [1,3,12,0,0]

    }

    public static int[] moveZeroes(int[] nums) {

        int index = nums.length;
        for (int i = 0; i < index; ) {

            if (nums[i] == 0) {
                for (int j = i; j < index - 1; j++) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
                index--;
                i++;
            }

        }
        return nums;

    }


    public int countNegatives2(int[][] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int low = 0;
            int high = arr[i].length - 1;
            while (low < high) {
                int mid = (high + low) / 2;
                if (arr[i][mid] >= 0) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            if (arr[i][low] < 0) {
                count += arr[i].length - low;
            }

        }
        return count;
    }


    public int countNegatives1(int[][] grid) {
        int count = 0;
        for (int[] row : grid) {
            int low = 0, high = row.length - 1;
            while (low < high) {
                int mid = (low + high) / 2;
                if (row[mid] >= 0) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            if (row[low] < 0) {
                count += (row.length - low);
            }
        }
        return count;
    }

    public int countNegatives(int[][] grid) {
        int count = 0;
        for (int[] row : grid) {
            for (int cos : row) {
                if (cos < 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int rob(int[] nums) {
        if (nums.length <= 3) {
            int max = nums[0];
            for (int num = 1; num < nums.length; num++) {
                max = nums[num] > max ? nums[num] : max;
            }
            return max;
        }

        int max1 = 0;
        int i = 0;
        while (i < nums.length) {
            if (i + 1 < nums.length) {
                max1 += nums[i];
            }
            i = i + 2;
        }

        int max2 = 0;
        int j = 1;
        while (j < nums.length) {
            max2 += nums[j];
            j = j + 2;
        }

        int max3 = 0;
        int k = 1;
        while (j < nums.length) {
            max2 += nums[j];
            j = j + 2;
        }
        return max1 > max2 ? max1 : max2;

    }

}
