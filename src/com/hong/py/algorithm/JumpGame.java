package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-28 09:29
 * description:
 * life for code
 */
public class JumpGame {

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        //jumpGame.jumpGame(new int[]{2,3,1,1,4});
        jumpGame.jumpGame(new int[]{3,2,1,0,4});
    }


    public void jumpGame(int[] datas) {
      dfs(datas,0);
    }

    public void dfs(int[] datas, int begin) {
        if (begin >= datas.length) {
            System.out.println("可以跳到");
            return;
        }
        //每个元素表示可以最多跳多少步
        int camJumpStep = datas[begin];
        if (camJumpStep == 0) {
            return;
        }
        for (int i = 1; i <= camJumpStep; i++) {
            dfs(datas,begin+i);
        }
    }
}
