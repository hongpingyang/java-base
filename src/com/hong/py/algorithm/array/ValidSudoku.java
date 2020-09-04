package com.hong.py.algorithm.array;

/**
 * author: hongpy
 * create time: 2020-06-19 10:01
 * description:
 * life for code
 */
public class ValidSudoku {

    public static void main(String[] args) {
        String[][] datas={
                {"5","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"}};

        String[][] datas2={
                {"8","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"}};

        ValidSudoku validSudoku = new ValidSudoku();
        System.out.println(validSudoku.validSudoku(datas));
        System.out.println(validSudoku.validSudoku(datas2));
    }


    /**
     * 验证数独是否合法
     * @param datas
     * @return
     */
    public boolean validSudoku(String[][] datas) {

        int column=datas[0].length;
        int row=datas.length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                for (int k = i + 1; k < row; k++) {
                    if (datas[i][j] == datas[k][j]&&(datas[i][j]!="."&&datas[k][j]!=".")) {
                        return false;
                    }
                }
            }
        }
       return true;
    }
}
