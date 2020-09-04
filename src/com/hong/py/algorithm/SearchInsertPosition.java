package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-21 21:28
 * description:
 * life for code
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        searchInsertPosition.searchInsertPosition(new int[]{1, 3, 5, 6}, 5); //3
        searchInsertPosition.searchInsertPosition(new int[]{1, 3, 5, 6}, 2); //1
        searchInsertPosition.searchInsertPosition(new int[]{1, 3, 5, 6}, 7); //4
        searchInsertPosition.searchInsertPosition(new int[]{1, 3, 5, 6}, 0); //0
    }


    public void searchInsertPosition(int[] datas, int target) {
        int length=datas.length;
        int index=-1;
        if (datas[length - 1] >= target && datas[0] <= target) {
            int i = find(datas, 0, length - 1, target);
            index= i;
        } else if (datas[length - 1] < target) {
            index= target - datas[length - 1] + length - 1;
        } else if (datas[0] > target) {
            index= 0;
        }
        System.out.println("应该的位置："+index);
    }

    /**
     * 二分查找 第一个比目标数大的数
     * @param datas
     * @param left
     * @param right
     * @param target
     * @return
     */
    public int find(int[] datas, int left, int right, int target) {
        while (left < right) {
            int mid=(left+right)/2;
            if (target >= datas[mid]) { //比目标小的，往上提
                left=mid+1;
            } else {
                right=mid;
            }
        }
        return left;
    }
}
