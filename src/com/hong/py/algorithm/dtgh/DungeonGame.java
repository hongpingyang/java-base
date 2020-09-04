package com.hong.py.algorithm.dtgh;

/**
 * author: hongpy
 * create time: 2020-06-30 20:14
 * description:
 * life for code
 *
 恶魔抓走了公主(P)并把她囚禁在地牢的右下角。地牢包含M x N个房间，排列成一个二维的格子。我们英勇的骑士(K)一开始位于左上角的房间里，需要一路披荆斩棘营救公主。

 骑士拥有一个正整数的初始生命值。如果在任何一点其生命值≤0，他立刻会死去。

 一些房间由恶魔守卫着，因此当骑士进入这些房间时就会损失生命值（负整数）；其他房间或者是空的（数值为0），或者包含一些魔力宝珠（magic orbs）可以增加骑士的生命值（正整数）。

 为了尽可能快的解救公主，骑士决定每一步只向右或者向下移动。

 编写一个函数决定骑士的最小初始生命值，确保他可以成功营救公主。

 例如，给定下面的地牢，骑士的初始生命值至少应当为7，如果他按照下面的最优路线行进：右 -> 右 -> 下 -> 下.
 */
public class DungeonGame {

    public static void main(String[] args) {
        DungeonGame dungeonGame = new DungeonGame();
        int[][] datas=new int[][]{
                {-2,-3,3},
                {-5,-10,1},
                {10,30,-5}};
        dungeonGame.dungeonGame(datas);

    }

    public void dungeonGame(int[][] datas) {

        int m=datas.length;
        int n = datas[0].length;
        int[][] result = new int[m][n];

     /*   //result[m-1][n-1]=datas[]
        for (int i = m-1; i >=0; i--) {
            for (int j = n-1; j >=0; j--) {
                if (i == m - 1 && j == n - 1) {
                    if (datas[i][j] < 0) {
                        result[i][j] = 1 - datas[i][j];
                    } else {
                        result[i][j] = 1;
                    }
                } else {



                    result[i][j]=

                }

            }
        }*/
        System.out.println(datas[m-1][n-1]);
    }
}
