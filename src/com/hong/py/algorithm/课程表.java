package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * author: hongpy
 * create time: 2020-07-17 11:16
 * description:
 * life for code
 */
public class 课程表 {



    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];

        List<List<Integer>> adjacency = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());

        // Get the indegree and adjacency of every course.

        for(int[] cp : prerequisites) {
            indegrees[cp[0]]++; //需要依赖其它课程的课程
            adjacency.get(cp[1]).add(cp[0]); //cp[1]是被依赖的课程        依赖的课程加入到要依赖的list集合种
        }

        // Get all the courses with the indegree of 0.

        for(int i = 0; i < numCourses; i++)
            if(indegrees[i] == 0) queue.add(i); //找不需要依赖其它课程的课程入队。

        // BFS TopSort.
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for(int cur : adjacency.get(pre)) //需要依赖到pre的课程列表
                if(--indegrees[cur] == 0) queue.add(cur);
        }

        return numCourses == 0;
    }


    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();

        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());

        int[] flags = new int[numCourses];
        for(int[] cp : prerequisites)
            adjacency.get(cp[1]).add(cp[0]);

        for(int i = 0; i < numCourses; i++)
            if(!dfs(adjacency, flags, i)) return false;
        return true;
    }
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if(flags[i] == 1) return false;
        //if(flags[i] == -1) return true;

        flags[i] = 1; //
        for(Integer j : adjacency.get(i))
            if(!dfs(adjacency, flags, j)) return false;
        flags[i] = 0;
        return true;
    }

}
