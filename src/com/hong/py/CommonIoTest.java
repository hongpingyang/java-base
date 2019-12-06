package com.hong.py;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: 工具类包括FileUtils、IOUtils、FilenameUtils和FileSystemUtils
 * @Author: hongpy21691
 * @CreateDate: 2019/9/9 16:30
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/9 16:30
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class CommonIoTest {

    public static void main(String[] args) throws IOException {

//        TestFilenameUtils();
        TestFileUtils();
    }

    private static void TestFilenameUtils() throws IOException {
        //判斷父目錄是否包含子目錄
        System.out.println(FilenameUtils.directoryContains("F:\\technology-talk-master\\open-source-framework",
                "F:\\technology-talk-master\\open-source-framework\\commons-io.md"));
        //判斷兩個文件名是否相同
        System.out.println(FilenameUtils.equals("F:\\technology-talk-master\\open-source-framework",
                "F:\\technology-talk-master\\open-source-framework\\commons-io.md"));
        //獲取文件基類名 和擴展名
        System.out.println(FilenameUtils.getBaseName("F:\\technology-talk-master\\open-source-framework\\commons-io.md"));
        System.out.println(FilenameUtils.getExtension("F:\\technology-talk-master\\open-source-framework\\commons-io.md"));
        //
        System.out.println(FilenameUtils.getName("F:\\technology-talk-master\\open-source-framework\\commons-io.md"));


        //判斷文件後綴是否合法
        System.out.println(FilenameUtils.isExtension(
                "F:\\technology-talk-master\\open-source-framework\\commons-io.md"
        , new String[]{"PNG", "md", "txt"}));
    }

    private static void TestFileUtils() throws IOException {

        File file1 = new File("F:\\technology-talk-master\\测试文件夹\\commons-io.md");
        File file2 = new File("F:\\technology-talk-master\\测试文件夹\\commons-io-test.md");
        File filedirectory = new File("F:\\technology-talk-master\\测试文件夹\\");

        //FileUtils.deleteQuietly(file2);
        //強制生成文件目錄
        //FileUtils.forceMkdir(filedirectory);
        //刪除目錄
        //FileUtils.deleteDirectory(filedirectory);

        //覆蓋內容到文件
        FileUtils.writeStringToFile(file1, "這個是是傻逼", "UTF-8",true);

        //讀取內容到字符串
        String s = FileUtils.readFileToString(file1, "UTF-8");
        System.out.println(s);

        //判斷文件內容是否相等
        System.out.println(FileUtils.contentEquals(file1, file2));

        //文件迭代
        LineIterator lineIterator = FileUtils.lineIterator(file1, "UTF-8");
        while (lineIterator.hasNext()) {
            System.out.println(lineIterator.next());
        }
    }
}
