package com.hong.py.Snowflake;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.Snowflake
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/3/31 16:04
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/3/31 16:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 *   1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0
 *   41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截) 得到的值），
 *  这里的的开始时间截，一般是我们的id生成器开始使用的时间，
 *  由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，
 *  可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69
 *   10位的数据机器位，可以部署在1024个节点，
 * 包括5位datacenterId和5位workerId。10-bit机器可以分别表示1024台机器。
 * 如果我们对IDC划分有需求，还可以将10-bit分5-bit给IDC，分5-bit给工作机器。
 * 这样就可以表示32个IDC，每个IDC下可以有32台机器，可以根据自身需求定义。
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号。
 * 12个自增序列号可以表示2^12个ID，理论上snowflake方案的QPS约为409.6w/s，
 * 这种分配方式可以保证在任何一个IDC的任何一台机器在任意毫秒内生成的ID都是不同的。
 * 加起来刚好64位，为一个Long型。
 *
 **/
public class SnowflakeDemo {

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1480166465631L;
    /**
     * 每一部分占用的位数
     * 时间位数是41位，总位数是64位，可用使用long在存储。
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数
    /** 序列在id中占的位数 */
    private final long sequenceBits = 12L;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    private long machine_id=0L;
    private long datacenter_id=0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    public SnowflakeDemo(long MACHINE_ID,long DATACENTER_ID) {
        machine_id=MACHINE_ID;
        datacenter_id=DATACENTER_ID;
    }


    public synchronized long nextid() {
        long timestamp = getGen();
        //
        if (lastTimestamp == timestamp) {
            //同一时间改变
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = waitforNext(lastTimestamp);
            }
        }else { //不同时间

            //序列重置
            sequence = 0L;
        }

        lastTimestamp=timestamp;

        return ((timestamp-START_STMP)<<(5+5+12))
                |(machine_id<<(5+12))
                |(datacenter_id<<(12))
                |(sequence);
    }

    /**
     * 等待获取到下个时间点
     * @param lastTimestamp
     * @return
     */
    public long waitforNext(long lastTimestamp) {
        long time = getGen();
        while (time <= lastTimestamp) {
            time = getGen();
        }
        return time;
    }


    /**
     *  获取当前系统时间
     * @return
     */
    protected long  getGen() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {
        SnowflakeDemo demo = new SnowflakeDemo(99, 23);

        for (int i = 0; i < 1000; i++) {
            long id = demo.nextid();
            System.out.println(Long.toBinaryString(id));
            System.out.println(id);
        }
    }
}
