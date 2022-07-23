package com.wotrd.dubbo.common.algorithm;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 有向图的构建，遍历
 * 课程表拓扑
 */
@Slf4j
public class CourseSolution {

    public static int[] getOrder(int numCourse, int[][] prerequisites) {
        if (numCourse == 0 || null == prerequisites || prerequisites.length == 0) {
            return new int[0];
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourse; i++) {
            graph.put(i, new LinkedList<>());
        }

        int[] record = new int[numCourse];
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
            record[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList();
        for (int i = 0; i < numCourse; i++) {
            if (record[i] == 0) {
                queue.offer(record[i]);
            }
        }

        List<Integer> list = new LinkedList<>();

        while (!queue.isEmpty()) {
            Integer course = queue.poll();
            list.add(course);
            for (int pre : graph.get(course)) {
                record[pre]--;
                if (record[pre] == 0) {
                    queue.offer(pre);
                }
            }
        }
        return list.size() == numCourse ? list.stream().mapToInt(v -> v).toArray() : new int[0];
    }

    public static void main(String[] args) {

        int[][] preArr = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] order = getOrder(4, preArr);
        log.info("result:{}", JSON.toJSONString(order));

    }
}
