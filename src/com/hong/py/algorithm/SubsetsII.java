package com.hong.py.algorithm;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * author: hongpy
 * create time: 2020-06-25 23:55
 * description:
 * life for code
 */
public class SubsetsII {
    private static Map<String, List<Integer>> map = new HashedMap();

    public static void main(String[] args) {
        SubsetsII subsets = new SubsetsII();
        subsets.subsets(new int[]{1, 2, 2});



        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println("[]");
    }


    public void subsets(int[] datas) {
        Arrays.sort(datas);//先排下序
        append(datas, new boolean[datas.length], 0);
    }

    public void append(int[] datas, boolean[] show,int step) {

        if (step == datas.length) {
            StringBuffer stringBuffer = new StringBuffer();
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < datas.length; i++) {
                if (show[i]) {
                    integers.add(datas[i]);
                    stringBuffer.append(datas[i]);
                }
            }
            if(integers.size()!=0)
              map.put(stringBuffer.substring(0), integers);
        } else {
            //表示要显示的
            show[step]=true;
            append(datas,show,step+1);
            //表示不要显示的
            show[step]=false;
            append(datas,show,step+1);
        }

    }

}
