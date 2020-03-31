package com.hong.py.BloomFilter;

import com.google.common.base.Preconditions;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

import java.nio.charset.Charset;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.BloomFilter
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/3/31 15:20
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/3/31 15:20
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * bits即上文讲到的长度为m的位数组，采用LockFreeBitArray类型做了封装。
 * numHashFunctions即哈希函数的个数k。
 * funnel是Funnel接口实现类的实例，它用于将任意类型T的输入数据转化为Java基本类型的数据（byte、int、char等等）。
 * 这里是会转化为byte。
 * strategy是布隆过滤器的哈希策略，即数据如何映射到位数组，
 * 其具体方法在BloomFilterStrategies枚举中。
 *
 * 布隆过滤器：
 * 对于是否存在有一定的误报，但对于不存在是100%准确。
 *
 **/
public class BloomFilterDemo {

    BloomFilter bloomFilter=BloomFilter.create(new Funnel<String>() {
        @Override
        public void funnel(String from, PrimitiveSink into) {
            into.putString(from, Charset.forName("UTF-8"));
        }
    },1024*1024);


    public void fun() {
        // 往过滤器中添加元素
        bloomFilter.put("布隆过滤器");
        // 查询
        boolean mightContain = bloomFilter.mightContain("布隆过滤器");
        System.out.println(mightContain);
    }

    public static void main(String[] args) {
        BloomFilterDemo demo = new BloomFilterDemo();
        demo.fun();
    }
}
