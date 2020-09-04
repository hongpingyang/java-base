package com.hong.py.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * author: hongpy
 * create time: 2020-06-26 14:06
 * description:
 * life for code
 */
public class SurroundedRegions {

    //类似围棋的,被围的被杀掉
    public static void main(String[] args) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        surroundedRegions.surroundedRegions(new String[][]{
                {"x", "x", "x", "0", "x"},
                {"x", "0","0","x", "x"},
                {"x", "x", "0", "x", "x"},
                {"x", "0", "x", "0", "x"},
                {"x", "0", "x", "x", "x"}});
    }

    public void surroundedRegions(String[][] datas) {

        int rows=datas.length;
        int columns = datas[0].length;
        boolean[][] dealed = new boolean[rows][columns];
        List<SurroundedRegions.Ponit> ponits = new ArrayList<>();
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(rows * columns);

        for (int i = 0; i <rows ; i++) {
            for (int j = 0; j < columns; j++) {
                if (datas[i][j] == "0") {
                   ponits.add(new SurroundedRegions.Ponit(i, j));
                }
            }
        }

        for (Ponit ponit : ponits) {

            //每个节点广度遍历
            if (dealed[ponit.getN()][ponit.getM()]) {
                continue;
            }
            dealed[ponit.getN()][ponit.getM()]=true;//表示处理过
            arrayBlockingQueue.add(ponit);
            boolean isreplace=true;
            List<Ponit> ponitList = new ArrayList<>();
            while (arrayBlockingQueue.size() > 0) {
                SurroundedRegions.Ponit poll = (SurroundedRegions.Ponit)arrayBlockingQueue.poll();
                ponitList.add(poll);
                int n = poll.getN();
                int m = poll.getM();
                // 是边缘节点,可以到达边缘。不能替换
                if (n == 0 || n == rows - 1 || m == 0 || m == columns - 1) {
                    isreplace=false;
                }
                if (n + 1 < columns && datas[n + 1][m] == "0" ) {
                    if(!dealed[n + 1][m]) {
                        arrayBlockingQueue.add(new SurroundedRegions.Ponit(n + 1, m));
                        dealed[n + 1][m] = true;//表示处理过
                    }
                }
                if ( n - 1 >= 0 && datas[n - 1][m] == "0") {
                    if(!dealed[n - 1][m]) {
                       arrayBlockingQueue.add(new SurroundedRegions.Ponit(n - 1, m));
                       dealed[n-1][m]=true;//表示处理过
                   }
                }
                if ( m + 1 < rows && datas[n][m + 1] == "0") {
                    if(!dealed[n][m+1]) {
                        arrayBlockingQueue.add(new SurroundedRegions.Ponit(n, m + 1));
                        dealed[n][m + 1] = true;//表示处理过
                    }
                }
                if ( m - 1 >= 0 && datas[n][m - 1] == "0") {
                    if(!dealed[n][m-1]) {
                        arrayBlockingQueue.add(new SurroundedRegions.Ponit(n, m - 1));
                        dealed[n][m - 1] = true;//表示处理过
                    }
                }
            }

            if (isreplace) {
                for (SurroundedRegions.Ponit point : ponitList) {
                    datas[point.getN()][point.getM()] = "+";
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < columns; j++) {
                if(j!=columns-1)
                    System.out.print(" "+datas[i][j]+",");
                else
                    System.out.print(" "+datas[i][j]);
            }
            System.out.println("]");
        }
    }





    public class Ponit {
        int n;
        int m;

        public Ponit(int n, int m) {
            this.n = n;
            this.m = m;
        }

        public int getN() {
            return n;
        }

        public void setN(int n) {
            this.n = n;
        }

        public int getM() {
            return m;
        }

        public void setM(int m) {
            this.m = m;
        }
    }

}

