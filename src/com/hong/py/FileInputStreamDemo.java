package com.hong.py;

import java.io.*;

public class FileInputStreamDemo {

    public static void main(String[] args) throws IOException {


        File file=new File("D:\\fileInputTest.txt");

        FileInputStream stream = new FileInputStream(file);

        FileOutputStream outputStream = new FileOutputStream("D:\\copyfileInputTest.txt");

        BufferedInputStream stream1 = new BufferedInputStream(stream);
        BufferedOutputStream outputStream1 = new BufferedOutputStream(outputStream);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
        BufferedReader reader = bufferedReader;

        /*StringBuffer result = new StringBuffer();
        while (reader.ready()) {
            result.append((char)reader.read());
        }
        System.out.println(result);*/

        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }


        /*int b;
        while ((b = stream1.read()) != -1) {
            System.out.println(b);
            outputStream1.write(b);
        }*/
       /* while ((b = stream.read()) != -1) {
            System.out.println(b);
            outputStream.write(b);
        }*/
        //outputStream1.write("我是中国人".getBytes());

        outputStream1.close();
        outputStream.close();
        stream.close();
        stream1.close();
    }
}
