package com.hong.py.algorithm;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * author: hongpy
 * create time: 2020-06-26 11:48
 * description:
 * life for code
 */
public class WordLadder {

    private static Map<String, String> map = new HashedMap();
    private static Map<String, Integer> mapLength = new HashedMap();

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        String[] source = new String[]{"hot", "dot", "dog", "lot", "log"};
        boolean[] added = new boolean[source.length];
        wordLadder.wordLadder(source, added, "hit", "cog");
    }

    public void wordLadder(String[] dict, boolean[] added, String start, String end) {

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(dict.length*dict.length);
        for (int i = 0; i < dict.length; i++) {
            if (isNext(start, dict[i])) {
                arrayBlockingQueue.add(dict[i]);
                added[i]=true;
                map.put(dict[i], start + "->" + dict[i]);
                mapLength.put(dict[i], 1);
            }
        }
        int step=0;
        while (arrayBlockingQueue.size()>0) {
            String poll = arrayBlockingQueue.poll();

            Integer integer = mapLength.get(poll);
            if(integer>step&&step!=0) {
                continue;
            }
            if (isNext(poll, end)) {   //广度遍历第一个找到的就第一个最近的
                System.out.println(map.get(poll)+"->"+end);
                step = integer;
                System.out.println("找到了,长度位："+(step+1));
                //return; //只会找到第一个最近的
            } else {
                //继续往下找
                for (int i = 0; i < dict.length; i++) {
                    if (isNext(poll, dict[i])&&!added[i]) {
                        arrayBlockingQueue.add(dict[i]);
                        added[i]=true;
                        //把dict[i]的由来跟新一下
                        map.put(dict[i], map.getOrDefault(poll,poll)+ "->" + dict[i]);
                        mapLength.put(dict[i], mapLength.getOrDefault(poll,0)+1);
                    }
                }
            }
        }
        if(step==0)
          System.out.println("没有找到");
    }

    /**
     * 是否是可以翻译的
     * @param value
     * @param regx
     * @return
     */
    public boolean isNext(String value, String regx) {

        if (value.length() != regx.length()) {
            return false;
        } else {
            int diffcount=0;
            for (int i = 0; i < value.length(); i++) {
                if (value.charAt(i) != regx.charAt(i)) {
                    diffcount++;
                }
            }
            if (diffcount > 1) {
                return false;
            }
            return true;
        }
    }

}
