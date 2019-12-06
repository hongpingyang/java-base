package com.hong.py.NIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.NIO
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/10/30 19:04
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/10/30 19:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class NioDemo {

    public static void main(String[] args) {
        //buffer();

        /**
         * FileChannel是一个连接到文件的通道。可以通过文件通道读写文件。
         * FileChannel无法设置为非阻塞模式，它总是运行在阻塞模式下。
         *
         * Java NIO 管道是2个线程之间的单向数据连接。
         * Pipe有一个source通道和一个sink通道。
         * 数据会被写到sink通道，从source通道读取。
         *
         * write from
         * read to
         *
        */
         //channeltoChannel();

        //selector();

        pipe();
    }

    public static  void buffer() {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("F:\\订单表.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel inChannel = aFile.getChannel();

         //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = 0;
        try {
            bytesRead = inChannel.read(buf);//read into buffer.
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (bytesRead != -1) {

            buf.flip();  //make buffer ready for read

            while(buf.hasRemaining()){
                System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.clear(); //make buffer ready for writing
            try {
                bytesRead = inChannel.read(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        try {
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void channeltoChannel(){
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("F:\\订单表.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        RandomAccessFile aFile1 = null;
        try {
            aFile1 = new RandomAccessFile("F:\\订单表1.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FileChannel channel = aFile.getChannel();
        FileChannel channel1 = aFile1.getChannel();
        long size=0;
        try {
             size=channel.size();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            channel.transferFrom(channel1,0,size);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static void selector() {
        Selector selector = null;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SocketChannel channel = null;
        try {
            channel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            channel.configureBlocking(false);
            SelectionKey selectionkey = channel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true) {
            int readyChannels = 0;
            try {
                readyChannels = selector.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(readyChannels == 0) continue;
            Set selectedKeys = selector.selectedKeys();
            Iterator keyIterator = selectedKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = (SelectionKey) keyIterator.next();
                SocketChannel channel1 =(SocketChannel) key.channel();
                Selector selector1 = key.selector();


                if(key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                    //create buffer with capacity of 48 bytes
                    ByteBuffer buf = ByteBuffer.allocate(48);

                    int bytesRead = 0;
                    try {
                        bytesRead = channel1.read(buf);//read into buffer.
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    while (bytesRead != -1) {

                        buf.flip();  //make buffer ready for read

                        while(buf.hasRemaining()){
                            System.out.print((char) buf.get()); // read 1 byte at a time
                        }

                        buf.clear(); //make buffer ready for writing
                        try {
                            bytesRead = channel1.read(buf);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }

    }

    public static void pipe() {
        Pipe pipe;
        Pipe.SinkChannel sinkChannel;
        Pipe.SourceChannel sourceChannel;
        try {
            pipe=Pipe.open();
            sinkChannel = pipe.sink();
            sourceChannel = pipe.source();

            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());

            buf.flip();

            while(buf.hasRemaining()) {
                sinkChannel.write(buf); //
            }

            ByteBuffer bufread = ByteBuffer.allocate(48);
            int read = sourceChannel.read(bufread);
            System.out.println(read);


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
