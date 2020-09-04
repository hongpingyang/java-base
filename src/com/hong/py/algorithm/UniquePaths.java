package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-27 10:25
 * description:
 * life for code
 */
public class UniquePaths {

    private static int paths;

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        int[][] datas=new int[][]{
                {0,0,0,0,0,0,1,0},
                {0,0,0,1,1,0,0,0},
                {0,0,0,0,0,0,1,0}};
        uniquePaths.uniquePaths(datas, 0, 0, 7, 2);
    }

    public void uniquePaths(int[][] datas,int beginX,int beginY,int tragetX,int targetY ) {
       int rows=datas.length;
       int columns=datas[0].length;
       boolean[][] walkeds = new boolean[rows][columns];
       dfs(datas, walkeds, rows, columns, beginX, beginY, tragetX, targetY);
       System.out.println(paths);
    }

    public void dfs(int[][] datas,boolean[][] walkeds,int n,int m, int x, int y, int targetx, int targety) {
        if (x == targetx && y == targety) {
            paths++; //能到这里的就是代表一种路径
            System.out.println("到达了目标点");
            return;
        }
        //System.out.println("到达点：["+x+","+y+"]");
        //机器人只能往下或者右走 1为障碍物
        if (x + 1 < m&&datas[y][x+1]!=1&&!walkeds[y][x+1]) {
            walkeds[y][x+1]=true; //走过的不能回头走
            dfs(datas,walkeds, n, m, x + 1, y, targetx, targety);
            walkeds[y][x+1]=false;
        }
        if (y + 1 < n&&datas[y+1][x]!=1&&!walkeds[y+1][x]) {
            walkeds[y+1][x]=true;
            dfs(datas,walkeds, n, m, x, y+1, targetx, targety);
            walkeds[y+1][x]=false;
        }
    }
}
