package com.hong.py.algorithm;

/**
 * author: hongpy
 * create time: 2020-06-21 22:03
 * description:
 * life for code
 */
public class SearchinRotatedSortedArray {

    public static void main(String[] args) {
        SearchinRotatedSortedArray searchinRotatedSortedArray = new SearchinRotatedSortedArray();
        searchinRotatedSortedArray.searchinRotatedSortedArray(new int[]{4,5,6,7,0,1,2},0);
    }


    public void searchinRotatedSortedArray(int[] datas, int target) {
        int i = find(datas, 0, datas.length - 1, target);
        System.out.println("找到的位置位：" + i);
    }

    public int find(int[] datas, int left, int right, int target) {
        if (left < right) {
            int mid=(left+right)/2;
            if (datas[mid] == target) {
               return mid;
            } else if (datas[mid] > datas[left]) {//left 到mid是有序的
                //判断是否在left到mid中间
                if (datas[mid] > target && datas[left] <= target) {
                    right = mid;
                    return find(datas, left, right, target);
                } else {
                    left = mid + 1;
                    return find(datas, left, right, target);
                }
            } else if (datas[mid] == datas[left]) { //允许重复 有重复的话，先跳过一个
                left = left + 1;
                return find(datas, left, right, target);
            }
            else if (datas[mid] < datas[left]) {//left 到mid不是有序的，中间有折点
                //判断是否在mid到right中间
                if (datas[mid] < target && datas[right] >= target) { //
                    left = mid+1;
                    return find(datas, left, right, target);
                } else {
                    right=mid;
                    return find(datas, left, right, target);
                }
            }
        }
        return -1;
    }
}
