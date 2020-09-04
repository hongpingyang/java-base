package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-24 22:42
 * description:
 * life for code
 */
public class Searcha2DMatrix {

    public static void main(String[] args) {
        Searcha2DMatrix searcha2DMatrix = new Searcha2DMatrix();
        searcha2DMatrix.searcha2DMatrix(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}},
                12);
        searcha2DMatrix.searcha2DMatrix(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}},
                30);
    }


    public void searcha2DMatrix(int[][] matrix, int traget) {
        int rows=matrix.length;
        int columns = matrix[0].length;

        boolean isExist = findByBinary(matrix, 0, 0, rows - 1, columns - 1, columns, traget);
        System.out.println(isExist);
    }

    public boolean findByBinary(int[][] matrix, int row1, int columns1, int row2, int column2, int fixcolumn, int target) {
        int left=row1*fixcolumn+columns1;
        int right=row2*fixcolumn+column2;

        int mid=(left+right)/2;
        int row=mid/fixcolumn;
        int column=mid%fixcolumn;

        if (left < right) {
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                return findByBinary(matrix, row1, columns1, row, column, fixcolumn, target);
            } else if (matrix[row][column] < target) {
                //前进一位
                return findByBinary(matrix, row, column+1, row2, column2, fixcolumn, target);
            }
        }
        return false;
    }
}
