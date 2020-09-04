package com.hong.py.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import com.hong.py.pojo.Student;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * kryo是一个高性能的序列化/反序列化工具，
 * 由于其变长存储特性并使用了字节码生成机制，拥有较高的运行速度和较小的体积。
 *
 * 1、Kryo序列化后比Hessian小很多。（kryo优于hessian）
 * 2、由于Kryo没有将类field的描述信息序列化，所以Kryo需要以自己加载该类的filed。这意味着如果该类没有在kryo中注册，或者该类是第一次被kryo序列化时，kryo需要时间去加载该类（hessian优于kryo）
 * 3、由于2的原因，如果该类已经被kryo加载过，那么kryo保存了其类的信息，就可以很快的将byte数组填入到类的field中,而hessian则需要解析序列化后的byte数组中的field信息，对于序列化过的类，kryo优于hessian。
 * 4、hessian使用了固定长度存储int和long，而kryo则使用的变长，实际中，很大的数据不会经常出现。(kryo优于hessian)
 * 5、hessian将序列化的字段长度写入来确定一段field的结束，而kryo对于String将其最后一位byte+x70用于标识结束（kryo优于hessian）
 */
public class KryoTest {

    private static  Output output;
    private static Kryo kryo;

    private static Input input;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        kryo = new Kryo();
        //对于没有无参构造需要
        //而StdInstantiatorStrategy在是依据JVM version信息及JVM vendor信息创建对象的，
        //可以不调用对象的任何构造方法创建对象。
        kryo.setInstantiatorStrategy(new DefaultInstantiatorStrategy(
                new StdInstantiatorStrategy()));
        //要加载该类
        kryo.register(Student.class);
        Student student = new Student("hongpy", 18);
        output = new Output(new FileOutputStream("file.txt"));
        writeObject(student);
        writeUTF("this is kyrotest");
        writeInt(12);
        output.close();

        //Student必须要有无参构造
        //读取顺序必须与写入顺序一致
        input = new Input(new FileInputStream("file.txt"));
        Student student1 = readObject(Student.class);
        System.out.println(student1);
        String s = readUTF();
        System.out.println(s);
        int i = readInt();
        System.out.println(i);
        input.close();
    }

    public static void writeBool(boolean v) {
        output.writeBoolean(v);
    }

    public static void writeByte(byte v)  {
        output.writeByte(v);
    }

    public void writeShort(short v)  {
        output.writeShort(v);
    }

    public static void writeInt(int v)  {
        output.writeInt(v);
    }

    public static void writeLong(long v)  {
        output.writeLong(v);
    }

    public static void writeFloat(float v)  {
        output.writeFloat(v);
    }

    public static void writeDouble(double v) {
        output.writeDouble(v);
    }

    public static void writeBytes(byte[] v)  {
        if (v == null) {
            output.writeInt(-1);
        } else {
            writeBytes(v, 0, v.length);
        }
    }

    public static void writeBytes(byte[] v, int off, int len)  {
        if (v == null) {
            output.writeInt(-1);
        } else {
            output.writeInt(len);
            output.write(v, off, len);
        }
    }

    public static void writeUTF(String v) {
        output.writeString(v);
    }

    public static void writeObject(Object v) {
        kryo.writeClassAndObject(output, v);
    }

    public static void flushBuffer() {
        output.flush();
    }

    public static boolean readBool() throws IOException {
        try {
            return input.readBoolean();
        } catch (KryoException e) {
            throw new IOException(e);
        }
    }

    public static byte readByte() throws IOException {
        try {
            return input.readByte();
        } catch (KryoException e) {
            throw new IOException(e);
        }
    }

    public static short readShort() throws IOException {
        try {
            return input.readShort();
        } catch (KryoException e) {
            throw new IOException(e);
        }
    }

    public static int readInt() throws IOException {
        try {
            return input.readInt();
        } catch (KryoException e) {
            throw new IOException(e);
        }
    }

    public static long readLong() throws IOException {
        try {
            return input.readLong();
        } catch (KryoException e) {
            throw new IOException(e);
        }
    }

    public static float readFloat() throws IOException {
        try {
            return input.readFloat();
        } catch (KryoException e) {
            throw new IOException(e);
        }
    }

    public static double readDouble() throws IOException {
        try {
            return input.readDouble();
        } catch (KryoException e) {
            throw new IOException(e);
        }
    }

    public static byte[] readBytes() throws IOException {
        try {
            int len = input.readInt();
            if (len < 0) {
                return null;
            } else if (len == 0) {
                return new byte[]{};
            } else {
                return input.readBytes(len);
            }
        } catch (KryoException e) {
            throw new IOException(e);
        }
    }

    public static String readUTF() throws IOException {
        try {
            return input.readString();
        } catch (KryoException e) {
            throw new IOException(e);
        }
    }

    public static Object readObject() throws IOException, ClassNotFoundException {
        // TODO optimization
        try {
            return kryo.readClassAndObject(input);
        } catch (KryoException e) {
            throw new IOException(e);
        }
    }


    @SuppressWarnings("unchecked")
    public static <T> T readObject(Class<T> clazz) throws IOException, ClassNotFoundException {
        // TODO optimization
        return (T) readObject();
    }

    @SuppressWarnings("unchecked")
    public static <T> T readObject(Class<T> clazz, Type type) throws IOException, ClassNotFoundException {
        // TODO optimization
        return readObject(clazz);
    }

    public static  void cleanup() {
        kryo = null;
    }


}
