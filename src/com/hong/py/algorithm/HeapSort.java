package com.hong.py.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * author: hongpy
 * create time: 2020-06-15 22:16
 * description:
 * life for code
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] dataMax = new int[]{ 8, 4, 20, 7, 3, 1, 25, 14, 17};
        int[] dataMix = new int[]{ 8, 4, 20, 7, 3, 1, 25, 14, 17};
        int[] dataMaxSort = new int[]{ 8, 4, 20, 7, 3, 1, 25, 14, 17};
        int[] dataMinSort = new int[]{ 8, 4, 20, 7, 3, 1, 25, 14, 17};
        HeapSort heapSort = new HeapSort();
        heapSort.maxHeap(dataMax,0,dataMax.length);
        System.out.println(Arrays.toString(dataMax));

        heapSort.minHeap(dataMix,0,dataMix.length);
        System.out.println(Arrays.toString(dataMix));

        heapSort.heapMaxSort(dataMaxSort);
        System.out.println(Arrays.toString(dataMaxSort));

        heapSort.heapMinSort(dataMinSort);
        System.out.println(Arrays.toString(dataMinSort));

        heapSort.topK(10);

        heapSort.useJdkPriorityQueue(dataMax);
    }


    /**
     * PriorityQueue其实也是用到堆排序
     * @param data
     */
    public void useJdkPriorityQueue(int[] data) {
        System.out.println("升序排序");
        PriorityQueue queue = new PriorityQueue();
        for (int i = 0; i < data.length; i++) {
            queue.offer(data[i]);
        }
        System.out.print("[");
        StringBuffer stringBuffer = new StringBuffer();
        while (queue.size() > 0) {
            stringBuffer.append(queue.poll() + ",");
        }
        System.out.print(stringBuffer.substring(0,stringBuffer.length()-1));
        System.out.println("]");

        System.out.println("降序排序");
        PriorityQueue<Integer> queue1 = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1); //
            }
        });
        for (int i = 0; i < data.length; i++) {
            queue1.offer(data[i]);
        }
        System.out.print("[");
        StringBuffer stringBuffer1 = new StringBuffer();
        while (queue1.size() > 0) {
            stringBuffer1.append(queue1.poll() + ",");
        }
        System.out.print(stringBuffer1.substring(0,stringBuffer1.length()-1));
        System.out.println("]");
    }

    /**
     * 建立一个大根堆 最大的元素肯定在堆顶
     * @param data
     */
    public void maxHeap(int[] data,int n,int length) {

        //从最后一个非叶子节点开始
        for (int i = (length / 2 - 1)+n; i >= n; i--) {

            //从左右子节点中找到最大的那个
            int leftson=(i-n)*2+1+n;
            int rightson=leftson+1;
            int maxvalue=data[leftson];
            int maxindex=leftson;
            if (rightson < length && data[rightson] >maxvalue ) {
                maxvalue= data[rightson];
                maxindex=rightson;
            }

            //与父节点相比，大于的话
            if (maxvalue > data[i]) {
                int temp=data[i];
                data[i]=maxvalue;
                data[maxindex]=temp;
            }
        }
    }

    /**
     * 建立一个小根堆 最小的元素肯定在堆顶
     * @param data
     */
    public void minHeap(int[] data,int n,int length) {

        //从最后一个非叶子节点开始
        for (int i = (length / 2 - 1)+n; i >= n; i--) {

            //从左右子节点中找到最小的那个
            int leftson=(i-n)*2+1+n;
            int rightson=leftson+1;
            int minvalue=data[leftson];
            int minindex=leftson;
            if (rightson < length && data[rightson] <minvalue ) {
                minvalue= data[rightson];
                minindex=rightson;
            }

            //与父节点相比，小于的话
            if (minvalue < data[i]) {
                int temp=data[i];
                data[i]=minvalue;
                data[minindex]=temp;
            }
        }
    }

    //
    public void heapMaxSort(int[] data) {
        int length = data.length;
        for (int i = 0; i <length; i++) {
            maxHeap(data, i, length - i);
        }
    }

    public void heapMinSort(int[] data) {
        int length = data.length;
        for (int i = 0; i <length; i++) {
            minHeap(data, i, length - i);
        }
    }

    /**
     * 求topK问题
     * @param k
     */
    public void topK(int k) {
        Random r = new Random();
        long currentTimeMillis = System.currentTimeMillis();
        int[] result = new int[k];
        for (int i = 0; i < 100000000; i++) {
            int nextInt = r.nextInt(100000000);
            if (i < k) {
                result[i] = nextInt;
            } else if (i == k) {
                //小顶堆
                minHeap(result, 0, k); //(logn)
            } else {
                if (nextInt > result[0]) { //替换掉
                    result[0] = nextInt;

                    minHeap(result, 0, k); //(logn)  再形成小顶堆
                }
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        heapMinSort(result);
        System.out.println(Arrays.toString(result));
    }

}
