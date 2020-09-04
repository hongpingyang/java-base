package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-24 23:28
 * description:
 * life for code
 */
public class Searcha2DMatrixII {

    public static void main(String[] args) {
        Searcha2DMatrixII searcha2DMatrix = new Searcha2DMatrixII();
        searcha2DMatrix.searcha2DMatrixII(new int[][]{
                {1, 4, 7, 11,15},
                {2, 5, 8, 12,19},
                {3, 6, 9, 16,22},
                {10, 13, 14, 17,24},
                {18, 21, 23, 26,30}
                }, 12);
        searcha2DMatrix.searcha2DMatrixII(new int[][]{
                {1, 4, 7, 11,15},
                {2, 5, 8, 12,19},
                {3, 6, 9, 16,22},
                {10, 13, 14, 17,24},
                {18, 21, 23, 26,30}}, 30);
        searcha2DMatrix.searcha2DMatrixII(new int[][]{
                {1, 4, 7, 11,15},
                {2, 5, 8, 12,19},
                {3, 6, 9, 16,22},
                {10, 13, 14, 17,24},
                {18, 21, 23, 26,30}}, 20);
    }


    public void searcha2DMatrixII(int[][] matrix, int traget) {
        int rows=matrix.length;
        int columns = matrix[0].length;

        //从左下角开始
        boolean isExist = findByBinary(matrix, rows-1, 0, rows-1,columns-1, traget);
        System.out.println(isExist);
    }

    public boolean findByBinary(int[][] matrix, int row1, int columns1, int fixrow, int fixcolumn, int target) {
        if (row1 > fixrow || columns1 > fixcolumn) {
            return false;
        }
        if (matrix[row1][columns1] == target) {
            return true;
        } else if (matrix[row1][columns1] > target) {
            return findByBinary(matrix, row1 - 1, columns1, fixrow, fixcolumn, target);
        } else {
            return findByBinary(matrix, row1 , columns1+1, fixrow, fixcolumn, target);
        }
    }
}
