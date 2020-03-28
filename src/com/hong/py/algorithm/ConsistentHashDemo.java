package com.hong.py.algorithm;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash算法
 * [0,2^32-1]中有4294967296个数字
 * String重写的hashCode()方法在一致性Hash算法中没有任何实用价值，
 * 得找个算法重新计算HashCode。
 * 这种重新计算Hash值的算法有很多，
 * 比如CRC32_HASH、FNV1_32_HASH、KETAMA_HASH等，
 * 其中KETAMA_HASH是默认的MemCache推荐的一致性Hash算法，
 * 用别的Hash算法也可以，比如FNV1_32_HASH算法的计算效率就会高一些。
 *
 *  在类KetamaNodeLocator.java里有个setKetamaNodes（）方法负责一致性哈希环的初始化工作， 代码如下：
 *   protected void setKetamaNodes(List<MemcachedNode> nodes) {
 *         TreeMap<Long, MemcachedNode> newNodeMap = new TreeMap<Long, MemcachedNode>();
 *         int numReps = config.getNodeRepetitions();
 *
 *         for (MemcachedNode node : nodes) {
 *           // Ketama does some special work with md5 where it reuses chunks.
 *           if (hashAlg == DefaultHashAlgorithm.KETAMA_HASH) {
 *             for (int i = 0; i < numReps / 4; i++) {
 *               byte[] digest = DefaultHashAlgorithm.computeMd5(config.getKeyForNode(node, i));
 *               for (int h = 0; h < 4; h++) {
 *                 Long k = ((long) (digest[3 + h * 4] & 0xFF) << 24)
 *                         | ((long) (digest[2 + h * 4] & 0xFF) << 16)
 *                         | ((long) (digest[1 + h * 4] & 0xFF) << 8)
 *                         | (digest[h * 4] & 0xFF);
 *
 *                 newNodeMap.put(k, node);
 *                 getLogger().debug("Adding node %s in position %d", node, k);
 *               }
 *             }
 *           } else {
 *             for (int i = 0; i < numReps; i++) {
 *               newNodeMap.put(hashAlg.hash(config.getKeyForNode(node, i)), node);
 *             }
 *           }
 *         }
 *         assert newNodeMap.size() == numReps * nodes.size();
 *         ketamaNodes = newNodeMap;
 *     }
 */
public class ConsistentHashDemo {

    //待添加入Hash环的服务器列表
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111","192.168.0.5:111","192.168.0.6:111"};

    //虚拟服务器节点
    private final TreeMap<Long, String> virtualServerNodes = new TreeMap<>();

    //一个真实结点对应1个虚拟节点
    private static final int VIRTUAL_NODES = 16;

    public static void main(String[] args) {
        ConsistentHashDemo demo = new ConsistentHashDemo();
        String[] keys = {"太阳", "月亮", "星星"};
        for(int i=0; i<keys.length; i++)
            System.out.println("[" + keys[i] + "]的hash值为" +
                    demo.hash(demo.md5(keys[i]),0) + ", 被路由到结点[" + demo.getServer(keys[i]) + "]");

    }

    public ConsistentHashDemo() {

        for (String str : servers){
            for(int i=0; i<VIRTUAL_NODES; i++){
                byte[] digest = md5(str + i);
                for (int h = 0; h < 4; h++) {
                    long m = hash(digest, h);
                    System.out.println("虚拟节点[" + str + "的"+ i + "个]被添加, hash值为" + m);
                    virtualServerNodes.put(m, str);
                }
            }
        }
        //System.out.println((power(2,32)-1));
    }

    //得到应当路由到的结点
    private String getServer(String key){
        //得到该key的hash值
        long hash = hash(md5(key),0);
        // 得到大于该Hash值的所有Map
        SortedMap<Long, String> subMap = virtualServerNodes.tailMap(hash);
        String virtualNode;
        if(subMap.isEmpty()){
            //如果没有比该key的hash值大的，则从第一个node开始
            Long i = virtualServerNodes.firstKey();
            //返回对应的服务器
            virtualNode = virtualServerNodes.get(i);
        }else{
            //第一个Key就是顺时针过去离node最近的那个结点
            Long i = subMap.firstKey();
            //返回对应的服务器
            virtualNode = subMap.get(i);
        }
        //virtualNode虚拟节点名称要截取一下
        if(StringUtils.isNotBlank(virtualNode)){
            return virtualNode;
        }
        return null;
    }


    private byte[] md5(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.reset();
        byte[] bytes;
        try {
            bytes = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.update(bytes);
        return md5.digest();
    }

    private long hash(byte[] digest, int number) {


        System.out.println(((long) (digest[3 + number * 4] & 0xFF)));
        System.out.println(((long) (digest[2 + number * 4] & 0xFF)));
        System.out.println(((long) (digest[1 + number * 4] & 0xFF)));
        System.out.println(((long) (digest[3 + number * 4] & 0xFF)<<24));
        System.out.println(((long) (digest[2 + number * 4] & 0xFF)<<16));
        System.out.println(((long) (digest[1 + number * 4] & 0xFF)<<8));
        System.out.println((digest[number * 4] & 0xFF));
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }
}
