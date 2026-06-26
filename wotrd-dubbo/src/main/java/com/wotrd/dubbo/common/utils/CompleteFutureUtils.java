package com.wotrd.dubbo.common.utils;


import com.sleepycat.je.tree.IN;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Function;


/**
 * CompleteFuture是为了弥补future回掉出现的，默认试用ForkJoinPool线程池,也可以自定义线程池
 */
public class CompleteFutureUtils {

    /**
     * 没有返回值
     */
    private static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("execute success!");
        }, Executors.newCachedThreadPool());
        completableFuture.get();
        System.out.println("success");
    }

    /**
     * 有返回值
     */
    private static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("execute success!");
            return "ok";
        }, Executors.newCachedThreadPool());
        Object o = completableFuture.get();
        System.out.println("success");

    }

    private static void whenComplete() {
        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Random random = new Random();
            int i = random.nextInt(10);
            if (i > 5) {
                System.out.println("execute success!");
            } else {
                throw new RuntimeException();
            }

        }, Executors.newCachedThreadPool());


        completableFuture.whenCompleteAsync(new BiConsumer() {
            @Override
            public void accept(Object o, Object o2) {
                System.out.println("success");
            }
        });
        completableFuture.exceptionally(new Function() {
            @Override
            public Object apply(Object o) {
                System.out.println("failed");
                return "fail";
            }
        });

        System.out.println("end");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//       runAsync();
//       supplyAsync();
//        whenComplete();


//        Object o = completableFuture.get();
        String[] strings = new String[3];
        strings[0] = "c";
        strings[1] = "acc";
        strings[2] = "ccc";

        isValid("()");
    }

    public static boolean isValid(String s) {
        if (null == s || s.length() < 2) {
            return false;
        }
        int length = s.length();
        if (length % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
            char start = s.charAt(i);
            if ('(' == start || '{' == start || '[' == start) {
                stack.add(start);
                continue;
            }
            if (')' == start || '}' == start || ']' == start) {
                if (stack.isEmpty()) {
                    return false;
                }
                char prefix = stack.pop();
                if (')' == start && prefix != '(') {
                    return false;
                } else if ('}' == start && prefix != '{') {
                    return false;

                } else if (']' == start && prefix != '[') {
                    return false;
                }
            }

        }
        return stack.isEmpty();

    }


//    public static List<List<Integer>> generate(int numRows) {
//        //结果值
//        List<List<Integer>> res = new ArrayList<>();
//        //每一行的元素
//        ArrayList<Integer> row = new ArrayList<>();
//        for (int i = 0; i < numRows; i++) {
//            //下面一行都会比上面一行多一个元素，我们在第一个位置给他加个1
//            row.add(0, 1);
//            //遍历每一行的结果，遍历的时候跳过第一个和最后一个，
//            //每个格子的值都是他正上面和左上角元素的和
//            for (int j = 1; j < row.size() - 1; j++)
//                row.set(j, row.get(j) + row.get(j + 1));
//            //把结果存放到res中
//            res.add(new ArrayList<>(row));
//        }
//        return res;
//    }


    public static TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) >> 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid);
        root.right = sortedArrayToBST(nums, mid, end);
        return root;

    }


    public boolean isValidBST(TreeNode root) {
        if (null == root) {
            return true;
        }
        return isSymmetric(root);
    }

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (null == left && null == right) {
            return true;
        }
        if (null == left || null == right) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);

    }


    public static int maxDepth(TreeNode root) {

        if (null == root) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }

    private static int length(TreeNode root, Integer length) {

        if (null == root) {
            return length;
        }

        if (null != root.left) {
            return length(root.left, length++);
        }

        if (null != root.right) {
            return length(root.right, length++);
        }

        return length;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}
