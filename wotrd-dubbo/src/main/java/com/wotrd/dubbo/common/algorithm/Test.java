package com.wotrd.dubbo.common.algorithm;

import java.util.*;

public class Test {


    public static final char FLAG[] = {'E', 'S', 'W', 'N'};

    public String finish(Integer xByInit, Integer yByInit, Character flag, String cmd) {
        cmd = cmd.toUpperCase();
        int len = FLAG.length;
        // 朝向
        int loc = 0;
        for (int i = 0; i < FLAG.length; i++) {
            if (FLAG[i] == flag) {
                loc = i;
                break;
            }
        }
        //  'L'和'R'使漫游车分别向左或向右旋转90度，而不会从当前地点移动
        //  'M'表示前进一个网格点，并保持相同的方向
        char[] cmdByChar = cmd.toCharArray();
        for (int i = 0; i < cmdByChar.length; i++) {
            if (cmdByChar[i] == 'L') {
                loc = (loc - 1 + len) % len;
            } else if (cmdByChar[i] == 'R') {
                loc = (loc + 1) % len;
            } else if (cmdByChar[i] == 'M') {
                switch (FLAG[loc]) {
                    case 'E':
                        xByInit += 1;
                        break;
                    case 'S':
                        yByInit -= 1;
                        break;
                    case 'W':
                        xByInit -= 1;
                        break;
                    case 'N':
                        yByInit += 1;
                        break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(xByInit);
        stringBuilder.append(" ");
        stringBuilder.append(yByInit);
        stringBuilder.append(" ");
        stringBuilder.append(FLAG[loc]);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        String a = "1234";
        String b = "12";
        int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        trap(a);
        System.out.println("ggg");
    }


    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 示例 1：
     * <p>
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     * <p>
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     */
    public String reverseWords(String s) {
        s = s.trim();
        String[] s1 = s.split(" ");
        List<String> list = Arrays.asList(s1);
        Collections.reverse(list);
        StringBuffer buffer = new StringBuffer();
        for (String a:list){
            if (!a.equals("")){
                buffer.append(a).append(" ");
            }

        }
        return buffer.toString().trim();

    }




    public static int trap(int[] height) {
        int sum = 0;
        for (int i = 0; i < height.length; i++) {

            int start = i;
            int startHeight = height[i];
            while (start >= 0) {
                if (height[start] > startHeight) {
                    startHeight = height[start];
                }
                start--;
            }

            int end = i;
            int endHeight = height[i];
            while (end <= height.length - 1) {
                if (height[end] > endHeight) {
                    endHeight = height[end];
                }
                end++;
            }

            if (startHeight > height[i] && endHeight > height[i]) {
                sum = sum + Math.min(startHeight, endHeight) - height[i];
            }

        }
        return sum;
    }

    /**
     * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
     * 你需要按照以下要求，给这些孩子分发糖果：
     * 每个孩子至少分配到 1 个糖果。
     * 相邻两个孩子评分更高的孩子会获得更多的糖果。
     * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     * 示例 1：
     * 输入：ratings = [1,0,2]
     * 输出：5
     * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
     * 示例 2：
     * 输入：ratings = [1,2,2]
     * 输出：4
     * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
     * 第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
     */
    public int candy(int[] ratings) {


        int left[] = new int[ratings.length];

        for (int i = 0; i < ratings.length; i++) {

            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }

        }

        int right = 0;
        int total = 0;

        for (int i = ratings.length - 1; i > 0; i--) {

            if (i < ratings.length - 1 && ratings[i] > ratings[i + 1]) {
                right = right + 1;
                total = total + Math.max(right, left[i]);
            } else {
                right = 1;
            }

        }

        return total;

    }


    /**
     * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
     * 根据维基百科上 h 指数的定义：h 代表“高引用次数”，一名科研人员的 h指数是指他（她）的 （n 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
     * 且其余的 n - h 篇论文每篇被引用次数 不超过 h 次。
     * 如果 h 有多种可能的值，h 指数 是其中最大的那个。
     * <p>
     * 示例 1：
     * <p>
     * 输入：citations = [3,0,6,1,5]
     * 输出：3
     * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
     * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
     * 示例 2：
     * <p>
     * 输入：citations = [1,3,1]
     * 输出：1
     */
    public int hIndex(int[] citations) {
        Random random = new Random();
        random.nextInt(10);
        Arrays.sort(citations);
        int count = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
            if (citations[i] <= count) {
                boolean flag = true;
                for (int j = 0; j < i; j++) {
                    if (citations[j] > citations.length - i) {
                        flag = false;
                    }
                }
                if (flag) {
                    return i;
                }
            }
            count++;
        }
        return 0;
    }


    public static int add(String a, String b) {
        int i = a.length();
        int j = b.length();
        StringBuilder builder = new StringBuilder();
        int flag = 0;
        while (i > 0 && j > 0) {
            int a1 = Integer.valueOf(String.valueOf(a.charAt(i - 1)));
            int b1 = Integer.valueOf(String.valueOf(b.charAt(j - 1)));
            i--;
            j--;
            int result = a1 + b1 + flag;
            if (result > 9) {
                flag = result / 10;
            }
            builder.insert(0, result % 10);

        }
        while (i > 0) {
            if (flag > 0) {
                char a1 = a.charAt(i - 1);
                int result = a1 + flag;
                if (result > 9) {
                    flag = result / 10;
                }
                builder.insert(0, result % 10);
            } else {
                builder.insert(0, a.substring(0, i));
                break;
            }
            i--;
        }
        while (j > 0) {
            if (flag > 0) {
                char a1 = b.charAt(j - 1);
                int result = a1 + flag;
                if (result > 9) {
                    flag = result / 10;
                }
                builder.insert(0, result % 10);
            } else {
                builder.insert(0, b.substring(0, j));
                break;
            }
            j--;
        }
        return Integer.valueOf(builder.toString());

    }

    public int maxProfit(int[] prices) {
        int max = 0;
        int[] dp = new int[prices.length];
        dp[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.min(dp[i - 1], prices[i]);
            max = Math.max(prices[i] - dp[i], max);
        }
        return max;

    }


    public static void rotate(int[] nums, int k) {
        int[] nums1 = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            nums1[i] = nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums1[(i + k) % nums.length];
        }
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer count = map.getOrDefault(nums[i], 0) + 1;
            if (count > nums.length / 2) {
                return nums[i];
            } else {
                map.put(nums[i], count);
            }
        }
        return nums[0];
    }


    public static int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return 2;
        }
        int slow = 2;
        int quick = 2;


        while (quick < nums.length) {
            if (nums[slow - 2] != nums[quick]) {
                nums[slow] = nums[quick];
                slow++;
            }
            quick++;
        }
        return slow;

    }

    public static int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;
        int length = nums.length;
        while (i <= j) {
            if (nums[i] == val) {
                if (nums[j] == val) {
                    nums[j--] = 0;
                } else {
                    nums[i] = nums[j];
                    nums[j] = 0;
                    j--;
                }
                length--;
            } else {
                i++;
            }
        }
        return length;

    }

    /**
     * public void merge(int[] nums1, int m, int[] nums2, int n) {
     * <p>
     * }
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        while (m > 0 && n > 0) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[m + n - 1] = nums1[m - 1];
                m--;
            } else {
                nums1[m + n - 1] = nums2[n - 1];
                n--;
            }
        }
        while (n > 0) {
            nums1[m + n - 1] = nums2[n - 1];
            n--;
        }
    }

}
