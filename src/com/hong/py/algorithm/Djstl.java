package com.hong.py.algorithm;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: hongpy
 * create time: 2020-06-16 11:37
 * description:
 * life for code
 */
public class Djstl {

    private static Map<Integer, String> map = new HashedMap();

    public static void main(String[] args) {
        //-1表示到达不了
        int[][] value = {{0,-1,10,-1,30,100},
                         {-1,0,5,-1,-1,-1},
                         {-1,-1,0,50,-1,-1},
                         {-1,-1,-1,0,-1,10},
                         {-1,-1,-1,20,0,60},
                         {-1,-1,-1,-1,-1,0}};

        int dis[] = new int[]{0,-1,10,-1,30,100};

        //1点到6点的距离
        seach(1,dis,value,6);

        /*int dis2[] = new int[]{-1,0,5,-1,-1,-1};
        //2点到6点的距离
        seach(2,dis2,value,6);*/
    }


    public static void seach(int x, int dis[], int value[][], int n) {

        boolean mark[] = new boolean[n];
        x=x-1;


        mark[x] = true;
        dis[x] = 0;
        int count = 1;
        while (count <= n) {	//O（n^2）

            int loc = 0; // 表示新加的点
            int min = Integer.MAX_VALUE;
            for (int i = 0; i <n; i++) { // 求dis里面的最小值 优先队列,堆 logn 求最小值的点 贪心
                if (!mark[i] && dis[i]!=-1&&dis[i] < min) {
                    min = dis[i];
                    loc = i;
                }
            }
            if (loc == 0)
                break; // 表示没有可以加的点了
            mark[loc] = true;
            System.out.println("++++++++++++++++++++++++");
            System.out.println(loc+1);
            if (map.get(loc) == null) {
                map.put(loc, (x+1) + "->" + (loc+1));
            }
            //只需要关注 我们加进来的点 能有哪些路径就可以了，不用扫描所有的dis 最好的情况应该可以达到o(nlogn),最坏的情况才是O(n^2)
            System.out.println("===================");
            for (int i = 0; i < n; i++) {

                if (value[loc][i] != -1 && (((dis[i]==-1)||(dis[loc] + value[loc][i] < dis[i])))) {
                    System.out.println(i+1);
                    map.put(i, map.get(loc)+"->"+(i+1)); //覆盖
                    dis[i] = dis[loc] + value[loc][i];
                }
            }
            System.out.println("===================");
            count++;
        }
        for (int i = 0; i < n; i++) {
            System.out.println((x+1) + "到 " + (i+1) + "的最短路径为 ：" + dis[i]+"; ");
            String s = map.get(i);
            s.hashCode();
            System.out.println(s);
        }

    }

}
