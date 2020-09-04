package com.hong.py.algorithm;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * author: hongpy
 * create time: 2020-06-16 09:03
 * description:
 * life for code
 * 广度优先
 */
public class BFS {

    int n; // 地图的行
    int m; // 地图的列
    int dx; // 安琪的位置x
    int dy; // 安琪的位置y
    int data[][]; // 邻接矩阵
    boolean mark[][]; // 标记数据 走过的位置

    public void bfs(int x,int y) {

        mark[x][y]=true;

        ArrayBlockingQueue<Point> arrayBlockingQueue = new ArrayBlockingQueue<Point>(n*m);
        Point start = new Point();
        start.x=x;
        start.y=y;
        arrayBlockingQueue.add(start);

        int next[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };	//ACM想到的

        while (!arrayBlockingQueue.isEmpty()) {
            Point point = arrayBlockingQueue.poll();
            for (int i = 0; i < 4; i++) {
                Point nextp = new Point();
                nextp.x=point.x+next[i][0];
                nextp.y=point.y+next[i][1];
                if(nextp.x < 1 || nextp.x > n || nextp.y < 1 || nextp.y > m) continue;
                if(data[nextp.x][nextp.y] == 0 && !mark[nextp.x][nextp.y]) { //表示可以走
                    if (nextp.x == dx && nextp.y == dy) {
                        System.out.println("安琪老师找到了");
                    } else {
                        arrayBlockingQueue.add(nextp); //加入队列
                        mark[nextp.x][nextp.y] = true; //标记已经走过了
                    }
                }
            }
        }
    }



}

class Point {

    int x;
    int y;
}
