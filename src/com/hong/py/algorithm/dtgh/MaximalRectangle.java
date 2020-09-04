package com.hong.py.algorithm.dtgh;

/**
 * author: hongpy
 * create time: 2020-06-30 13:32
 * description:
 * life for code
 */
public class MaximalRectangle {

    public static void main(String[] args) {

        char ch = '9';
        if (Character.isDigit(ch)){  // 判断是否是数字
            int num = (int)ch - (int)('0');
            System.out.println(num);
        }
       /* MaximalRectangle maximalRectangle = new MaximalRectangle();
        int[][] datas=new int[][]{
                {0,0,1,1,1,0,1,0},
                {0,0,1,1,1,0,0,0},
                {0,0,1,1,1,0,1,0},
                {0,0,0,0,0,0,1,0}};
        maximalRectangle.maximalRectangle(datas);*/
    }

    public void maximalRectangle(char[][] datas) {
        int rows=datas.length;
        int columns = datas[0].length;
        int[][] result = new int[rows][columns];
        int maxglobal=0;
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (datas[i][j] == '1') { //只有为1的才能讨论
                    int left=Integer.parseInt(String.valueOf(datas[i - 1][j]));
                    int down=Integer.parseInt(String.valueOf(datas[i][j - 1]));
                    int corner=Integer.parseInt(String.valueOf(datas[i-1][j - 1]));
                    int temp = Math.min(Math.min(left, down), corner);
                    datas[i][j] = (char)(temp + 1);
                    //绑定最大值
                    if (temp + 1 > maxglobal) {
                        maxglobal = temp + 1;
                    }
                }
            }
        }
        System.out.println("最大面积为："+maxglobal*maxglobal);
    }


}
