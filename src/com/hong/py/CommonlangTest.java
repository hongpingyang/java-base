package com.hong.py;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description:
 *
 *
 * 一. org.apache.commons.io.IOUtils
 *
 * closeQuietly：关闭一个IO流、socket、或者selector且不抛出异常，通常放在finally块
 * toString：转换IO流、 Uri、 byte[]为String
 * copy：IO流数据复制，从输入流写到输出流中，最大支持2GB
 * toByteArray：从输入流、URI获取byte[]
 * write：把字节. 字符等写入输出流
 * toInputStream：把字符转换为输入流
 * readLines：从输入流中读取多行数据，返回List<String>
 * copyLarge：同copy，支持2GB以上数据的复制
 * lineIterator：从输入流返回一个迭代器，根据参数要求读取的数据量，全部读取，如果数据不够，则失败
 *
 *
 *
 * 二. org.apache.commons.io.FileUtils
 *
 * deleteDirectory：删除文件夹
 * readFileToString：以字符形式读取文件内容
 * deleteQueitly：删除文件或文件夹且不会抛出异常
 * copyFile：复制文件
 * writeStringToFile：把字符写到目标文件，如果文件不存在，则创建
 * forceMkdir：强制创建文件夹，如果该文件夹父级目录不存在，则创建父级
 * write：把字符写到指定文件中
 * listFiles：列举某个目录下的文件(根据过滤器)
 * copyDirectory：复制文件夹
 * forceDelete：强制删除文件
 *
 *
 *
 * 三. org.apache.commons.lang.StringUtils
 *
 * isBlank：字符串是否为空 (trim后判断)
 * isEmpty：字符串是否为空 (不trim并判断)
 * equals：字符串是否相等
 * join：合并数组为单一字符串，可传分隔符
 * split：分割字符串
 * EMPTY：返回空字符串
 * trimToNull：trim后为空字符串则转换为null
 * replace：替换字符串
 *
 *
 *
 * 四. org.apache.http.util.EntityUtils
 *
 * toString：把Entity转换为字符串
 * consume：确保Entity中的内容全部被消费。可以看到源码里又一次消费了Entity的内容，假如用户没有消费，那调用Entity时候将会把它消费掉
 * toByteArray：把Entity转换为字节流
 * consumeQuietly：和consume一样，但不抛异常
 * getContentCharset：获取内容的编码
 *
 *
 *
 * 五. org.apache.commons.lang3.StringUtils
 *
 * isBlank：字符串是否为空 (trim后判断)
 * isEmpty：字符串是否为空 (不trim并判断)
 * equals：字符串是否相等
 * join：合并数组为单一字符串，可传分隔符
 * split：分割字符串
 * EMPTY：返回空字符串
 * replace：替换字符串
 * capitalize：首字符大写
 *
 *
 *
 * 六. org.apache.commons.io.FilenameUtils
 *
 * getExtension：返回文件后缀名
 * getBaseName：返回文件名，不包含后缀名
 * getName：返回文件全名
 * concat：按命令行风格组合文件路径(详见方法注释)
 * removeExtension：删除后缀名
 * normalize：使路径正常化
 * wildcardMatch：匹配通配符
 * seperatorToUnix：路径分隔符改成unix系统格式的，即/
 * getFullPath：获取文件路径，不包括文件名
 * isExtension：检查文件后缀名是不是传入参数(List<String>)中的一个
 *
 *
 *
 * 七. org.springframework.util.StringUtils
 *
 * hasText：检查字符串中是否包含文本
 * hasLength：检测字符串是否长度大于0
 * isEmpty：检测字符串是否为空（若传入为对象，则判断对象是否为null）
 * commaDelimitedStringToArray：逗号分隔的String转换为数组
 * collectionToDelimitedString：把集合转为CSV格式字符串
 * replace 替换字符串
 * delimitedListToStringArray：相当于split
 * uncapitalize：首字母小写
 * collectionToDelimitedCommaString：把集合转为CSV格式字符串
 * tokenizeToStringArray：和split基本一样，但能自动去掉空白的单词
 *
 *
 *
 * 八. org.apache.commons.lang.ArrayUtils
 *
 * contains：是否包含某字符串
 * addAll：添加整个数组
 * clone：克隆一个数组
 * isEmpty：是否空数组
 * add：向数组添加元素
 * subarray：截取数组
 * indexOf：查找某个元素的下标
 * isEquals：比较数组是否相等
 * toObject：基础类型数据数组转换为对应的Object数组
 *
 *
 *
 * 九. org.apache.commons.lang.StringEscapeUtils
 *
 * 参考十五：
 * org.apache.commons.lang3.StringEscapeUtils
 *
 *
 * 十. org.apache.http.client.utils.URLEncodedUtils
 *
 * format：格式化参数，返回一个HTTP POST或者HTTP PUT可用application/x-www-form-urlencoded字符串
 * parse：把String或者URI等转换为List<NameValuePair>
 *
 *
 *
 * 十一. org.apache.commons.codec.digest.DigestUtils
 *
 * md5Hex：MD5加密，返回32位字符串
 * sha1Hex：SHA-1加密
 * sha256Hex：SHA-256加密
 * sha512Hex：SHA-512加密
 * md5：MD5加密，返回16位字符串
 *
 *
 *
 * 十二. org.apache.commons.collections.CollectionUtils
 *
 * isEmpty：是否为空
 * select：根据条件筛选集合元素
 * transform：根据指定方法处理集合元素，类似List的map()
 * filter：过滤元素，类似List的filter()
 * find：基本和select一样
 * collect：和transform 差不多一样，但是返回新数组
 * forAllDo：调用每个元素的指定方法
 * isEqualCollection：判断两个集合是否一致
 *
 *
 *
 * 十三. org.apache.commons.lang3.ArrayUtils
 *
 * contains：是否包含某个字符串
 * addAll：添加整个数组
 * clone：克隆一个数组
 * isEmpty：是否空数组
 * add：向数组添加元素
 * subarray：截取数组
 * indexOf：查找某个元素的下标
 * isEquals：比较数组是否相等
 * toObject：基础类型数据数组转换为对应的Object数组
 *
 *
 *
 * 十四. org.apache.commons.beanutils.PropertyUtils
 *
 * getProperty：获取对象属性值
 * setProperty：设置对象属性值
 * getPropertyDiscriptor：获取属性描述器
 * isReadable：检查属性是否可访问
 * copyProperties：复制属性值，从一个对象到另一个对象
 * getPropertyDiscriptors：获取所有属性描述器
 * isWriteable：检查属性是否可写
 * getPropertyType：获取对象属性类型
 *
 *
 *
 * 十五. org.apache.commons.lang3.StringEscapeUtils
 *
 * unescapeHtml4：转义html
 * escapeHtml4：反转义html
 * escapeXml：转义xml
 * unescapeXml：反转义xml
 * escapeJava：转义unicode编码
 * escapeEcmaScript：转义EcmaScript字符
 * unescapeJava：反转义unicode编码
 * escapeJson：转义json字符
 * escapeXml10：转义Xml10
 * 这个现在已经废弃了，建议使用commons-text包里面的方法。
 *
 *
 *
 * 十六. org.apache.commons.beanutils.BeanUtils
 *
 * copyPeoperties：复制属性值，从一个对象到另一个对象
 * getProperty：获取对象属性值
 * setProperty：设置对象属性值
 * populate：根据Map给属性复制
 * copyPeoperty：复制单个值，从一个对象到另一个对象
 * cloneBean：克隆bean实例
 *
 *
 * @Author: hongpy21691
 * @CreateDate: 2019/9/9 18:58
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/9 18:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class CommonlangTest {

    public static void main(String[] args) {
//        testSystemUtils();
//        testStringUtils();
//        testNumberUtils();
//        testBooleanUtils();
//        testDate();
        testArrayUtils();

    }


    //原理都是调用System.getProperty()
    private static void testSystemUtils() {
        System.out.println(SystemUtils.getJavaHome());
        System.out.println(SystemUtils.getJavaIoTmpDir());
        System.out.println(SystemUtils.getUserDir());
        System.out.println(SystemUtils.getUserHome());
        System.out.println(SystemUtils.JAVA_VERSION);
        System.out.println(SystemUtils.OS_NAME);
        System.out.println(SystemUtils.USER_TIMEZONE);
    }

    private  static void testStringUtils() {
        String t = "tttt";
        System.out.println(StringUtils.split(t, ","));

        System.out.println(StringUtils.isBlank("   "));// true----可以验证null, ""," "等
        System.out.println(StringUtils.isBlank("null"));// false
        System.out.println(StringUtils.isAllLowerCase("null"));// t
        System.out.println(StringUtils.isAllUpperCase("XXXXXX"));// t
        System.out.println(StringUtils.isEmpty(" "));// f---为null或者""返回true
        System.out.println(StringUtils.defaultIfEmpty(null, "default"));// 第二个参数是第一个为null或者""的时候的取值
        System.out.println(StringUtils.defaultIfBlank("    ", "default"));//// 第二个参数是第一个为null或者""或者"   "的时候的取值

        //字符出現的次數 2次
        System.out.println(StringUtils.countMatches("dasda:sda:dsadascxc", ":"));
    }

    private static void testNumberUtils() {
        System.out.println(NumberUtils.isCreatable("231232.8"));//true---判断是否是数字
        System.out.println(NumberUtils.isDigits("2312332.5"));//false，判断是否是整数
        System.out.println(NumberUtils.toDouble("123cx"));//如果传的值不正确返回一个默认值，字符串转double，传的不正确会返回默认值
        System.out.println(NumberUtils.createBigDecimal("333333"));//字符串转bigdecimal
    }

    private static void testBooleanUtils() {
        System.out.println(BooleanUtils.isFalse(true));//false
        System.out.println(BooleanUtils.toBoolean("yes"));//true
        System.out.println(BooleanUtils.toBooleanObject(0));//false
        System.out.println(BooleanUtils.toStringYesNo(false));//no
    }

    private static void testDate() {
        // DateFormatUtils----date转字符串
        Date date = new Date();
        System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd hh:mm:ss"));// 小写的是12小时制
        System.out.println(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));// 大写的HH是24小时制

        // DateUtils ---加减指定的天数(也可以加减秒、小时等操作)
        Date addDays = DateUtils.addDays(date, 2);
        System.out.println(DateFormatUtils.format(addDays, "yyyy-MM-dd HH:mm:ss"));
        Date addDays2 = DateUtils.addDays(date, -2);
        System.out.println(DateFormatUtils.format(addDays2, "yyyy-MM-dd HH:mm:ss"));

        // 原生日期判断日期先后顺序
        System.out.println(addDays2.after(addDays)); //false
        System.out.println(addDays2.before(addDays));//true

        // DateUtils---字符串转date
        String strDate = "2018-11-01 19:23:44";
        try {
            Date parseDateStrictly = DateUtils.parseDateStrictly(strDate, "yyyy-MM-dd HH:mm:ss");
            Date parseDate = DateUtils.parseDate(strDate, "yyyy-MM-dd HH:mm:ss");
            System.out.println(parseDateStrictly);
            System.out.println(parseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void  testArrayUtils() {
        int array[] = { 1, 5, 5, 7 };
        System.out.println(array);

        // 增加元素
        array = ArrayUtils.add(array, 9);
        System.out.println(ArrayUtils.toString(array));

        // 删除元素
        array = ArrayUtils.remove(array, 3);
        System.out.println(ArrayUtils.toString(array));

        // 反转数组
        ArrayUtils.reverse(array);
        System.out.println(ArrayUtils.toString(array));

        // 查询数组索引
        System.out.println(ArrayUtils.indexOf(array, 5));

        // 判断数组中是否包含指定值
        System.out.println(ArrayUtils.contains(array, 5));

        // 合并数组
        array = ArrayUtils.addAll(array, new int[] { 1, 5, 6 });
        System.out.println(ArrayUtils.toString(array));


        Integer integer[] = new Integer[] { 0, 1, 2 };
        System.out.println(integer.getClass());

        int[] primitive = ArrayUtils.toPrimitive(integer);
        System.out.println(primitive.getClass());


    }




}
