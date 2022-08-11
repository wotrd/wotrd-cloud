package com.wotrd.dubbo.common.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.enums.Enum;

@Slf4j
public class DpSolution {

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

//        int[] nums = new int[]{-1, 3, 5, 6};
//        int target = 0;
//        System.out.println("i=" + searchInsert(nums, target));

        int[] nums = new int[]{2, 3, 2};
        System.out.println("i=" + rob1(nums));

    }

    //    给你一个数组arr，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用-1 替换。
//    完成所有替换操作后，请你返回这个数组。
//    示例 1：
//    输入：arr = [17,18,5,4,6,1]
//    输出：[18,6,6,6,1,-1]
//    解释：
//            - 下标 0 的元素 --> 右侧最大元素是下标 1 的元素 (18)
//            - 下标 1 的元素 --> 右侧最大元素是下标 4 的元素 (6)
//            - 下标 2 的元素 --> 右侧最大元素是下标 4 的元素 (6)
//            - 下标 3 的元素 --> 右侧最大元素是下标 4 的元素 (6)
//            - 下标 4 的元素 --> 右侧最大元素是下标 5 的元素 (1)
//            - 下标 5 的元素 --> 右侧没有其他元素，替换为 -1
//    示例 2：
//    输入：arr = [400]
//    输出：[-1]
//    解释：下标 0 的元素右侧没有其他元素。
    public static int[] replaceElements(int[] arr) {
        int dp[] = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i == arr.length - 1) {
                dp[i] = arr[i];
            } else {
                dp[i] = Math.max(dp[i + 1], arr[i]);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                arr[i] = -1;
            } else {
                arr[i] = dp[i + 1];
            }
        }
        return arr;
    }

//    假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
//    示例 1:
//    输入: [7,1,5,3,6,4]
//    输出: 5
//    解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
//    注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
//    示例 2:
//    输入: [7,6,4,3,1]
//    输出: 0
//    解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。

    public static int maxProfit(int[] prices) {
        // 第i天卖出可以获得最大值， num[i] -  前nums[i-1]天最小值
        // dp[i] = nums[i] - min(dp[i-1],nums[i])
        int dp[] = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            if (i == 0) {
                dp[i] = prices[i];
            } else {
                dp[i] = Math.min(dp[i - 1], prices[i]);
            }
        }

        int result = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] - dp[i] > result) {
                result = prices[i] - dp[i];
            }
        }
        return result;

    }


    /**
     * 打家劫舍首尾不相接
     *
     * @param nums
     * @return
     */
    public static int rob1(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        if (n == 2) return nums[0] > nums[1] ? nums[0] : nums[1];

        int[] dp1 = new int[n];  //不抢第一家
        int[] dp2 = new int[n]; //不抢最后一家
        dp1[0] = 0;
        dp1[1] = nums[1];
        for (int i = 2; i < n; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
        }
        dp2[0] = 0;
        dp2[1] = nums[0];
        for (int j = 2; j < n; j++) {
            dp2[j] = Math.max(dp2[j - 1], dp2[j - 2] + nums[j - 1]);
        }
        return Math.max(dp1[n - 1], dp2[n - 1]);

    }


    /**
     * 打家劫舍首尾可相接
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (null == nums) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }


        return dp[nums.length - 1];

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


}
