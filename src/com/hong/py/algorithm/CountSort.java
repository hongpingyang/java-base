package com.hong.py.algorithm;

import java.io.*;

/**
 * author: hongpy
 * create time: 2020-06-08 18:32
 * description:
 * life for code
 * 利用数组排序
 */
public class CountSort {

    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream("D:\\Users\\hpymn\\java-base-new\\src\\com\\hong\\py\\algorithm\\200w.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        String str=null;
        int data[] = new int[2100002];
        int i = 0;
        while ((str = reader.readLine()) != null) {
            double v = Double.parseDouble(str);
            int value = (int) (v * 100);
            data[i++]=value;
        }
        reader.close();
        inputStream.close();
        countSort(data);
    }


    public static void countSort(int[] data) throws IOException {
        //排序
        int count[] = new int[100000];
        //利用值当作数组下标
        for (int j = 0; j < data.length; j++) {
            count[data[j]]++;
        }

        File file = new File("D:\\Users\\hpymn\\java-base-new\\src\\com\\hong\\py\\algorithm\\200w-csort.txt");
        Writer out = new FileWriter(file);

        for (int x = 0; x < count.length; x++) {
            if (count[x] > 0) {
                for (int j = 0; j <count[x]; j++) {
                    out.write(((double) (x / 100.0)) + "\r\n");
                }
            }
        }
        out.close();
    }
}
