package com.wotrd.dubbo.common.algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuihuaSolution {

    public static void main(String[] args) {
//        int[] nums = {0, 0, 1};
//        int rob = rob(nums);
        log.info("rob:{}", cal(30));

//        nums = [0,1,0,3,12]
//        输出: [1,3,12,0,0]
//
//        /**
//         * 123
//         * 456
//         * 789
//         */
//
//        int[][] mat = new int[3][3];
//        mat[0][0] = 1;
//        mat[0][1] = 2;
//        mat[0][2] = 3;
//        mat[1][0] = 4;
//        mat[1][1] = 5;
//        mat[1][2] = 6;
//        mat[2][0] = 7;
//        mat[2][1] = 8;
//        mat[2][2] = 9;
//        int i = diagonalSum(mat, 3);

        int[] nums = new int[]{-1,3,5,6};
        int target = 0;
        System.out.println("i=" + searchInsert(nums, target));

    }

    /**
     * 示例 1:
     * <p>
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: nums = [1,3,5,6], target = 2
     * 输出: 1
     * 示例 3:
     * <p>
     * 输入: nums = [1,3,5,6], target = 7
     * 输出: 4
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/search-insert-position
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static int searchInsert(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            } else if (i == nums.length) {
                return i;
            }
        }
        return nums.length;

    }


    /**
     * 矩阵对角线数据求和
     *
     * @param n
     * @return
     */
    public static int diagonalSum(int[][] n, int matSize) {
        int result = 0;
        int c = matSize;
        int r = matSize;

        for (int i = 0; i < c; i++) {
            result += n[i][i];
        }
        for (int i = 0; i < r; i++) {
            result += n[i][r - i - 1];
        }

        if (matSize % 2 == 1) {
            result -= n[r / 2][r / 2];
        }
        return result;

    }

    /**
     * 斐波那契数列
     *
     * @param n
     * @return
     */
    public static int cal(int n) {
        int[] result = new int[31];
        result[0] = 0;
        result[1] = 1;
        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];

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
