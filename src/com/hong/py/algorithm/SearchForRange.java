package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-20 10:34
 * description:
 * life for code
 */
public class SearchForRange {

    public static void main(String[] args) {
        SearchForRange searchForRange = new SearchForRange();
        //searchForRange.searchForRange(new int[]{5, 7, 7, 7,8, 8, 10}, 0, 6, 7);

        searchForRange.lower(new int[]{5, 7, 7, 7,8, 8, 10}, 0, 6, 8);
        searchForRange.upper(new int[]{5, 7, 7, 7,8, 8, 10}, 0, 6, 8);
    }


    /**
     * 找到到等于某个数的第一个位置
     * @param sorted
     * @param left
     * @param right
     * @param search
     * @return
     */
    public int lower(int[] sorted, int left, int right, int search) {

        while (left < right) {
            int mid=(left+right)/2;
            if(search>sorted[mid]) left=(mid+1); //mid的是比search小,往前推进一个
            else right=(mid); //mid的数是大于等于search，需要往下调整
        }
        System.out.println(left);
        return left;
    }

    /**
     * 找到到大于某个数的第一个位置
     * @param sorted
     * @param left
     * @param right
     * @param search
     * @return
     */
    public int upper(int[] sorted, int left, int right, int search) {

        while (left < right) {
            int mid=(left+right)/2;
            if(search>=sorted[mid]) left=(mid+1); //mid的是比search小,往前推进一个
            else right=(mid); //mid的数是大于等于search，需要往下调整
        }
        System.out.println(left);
        return left;
    }

    public void searchForRange(int[] sorted,int left,int right,int search) {

        if (left < right) {

            int mid=(left+right)/2;
            if (search == sorted[mid]) {
                //找到了
                System.out.println(mid);
                return;
            } else if (search < sorted[mid]) {
                searchForRange(sorted, left, mid, search);
            } else {
                searchForRange(sorted, mid, right, search);
            }

        }
    }
}
