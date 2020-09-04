package com.hong.py.algorithm.dtgh;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * author: hongpy
 * create time: 2020-06-30 17:20
 * description:
 * life for code
 */
public class WordBreak {

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        wordBreak.wordBreak("catsanddog", new String[]{"cat","cats","and","sand","dog"});
    }


    public void wordBreak(String str, String[] dicts) {
        int length = str.length();
        boolean[] f = new boolean[length+1];
        f[0]=true;
        Map<Integer, List<String>> map = new HashedMap();
        map.put(0, new ArrayList<String>(){});
        for (int i = 1; i <=length; i++) {
            boolean can=false;
            for (String string : dicts) { //遍历字典。跟PrefectSquare 这个题很像。
                if (i >=string.length()) {
                    if (str.substring(i - string.length(),i).equals(string)&&f[i - string.length()]) {

                        List<String> integerListMap = map.get(i - string.length()); //来源有这么多条

                        List<String> strings = map.get(i);
                        if (strings == null) {
                            strings=new ArrayList<String>();
                        }
                        if (integerListMap != null && integerListMap.size() > 0) {
                            for (String source : integerListMap) {
                                String resut = source + "," + string; //拼接来源
                                strings.add(resut);
                            }
                        } else {
                            String resut = string;
                            strings.add(resut);
                        }

                        map.put(i, new ArrayList<>(strings));
                        can=true;
                        //break;
                    }
                }
            }
            f[i]=can;
        }

        System.out.println(f[length]);
        List<String> strings = map.get(length);
        for (String entry: strings){
            System.out.println(entry);
        }
        System.out.println(Arrays.toString(f));
    }
}
