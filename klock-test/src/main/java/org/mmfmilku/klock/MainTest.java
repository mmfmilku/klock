package org.mmfmilku.klock;

import org.mmfmilku.klock.file.FileUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MainTest {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));

        System.out.println(System.getProperty("ttt"));
        System.out.println(System.getProperty("klock.key"));

        Class c = MainTest.class;
        System.out.println("java输出测试:");
//        System.out.println("classLoader.getResource(\"\"):" + c.getClassLoader().getResource(""));
//        System.out.println("classLoader.getResource(\"/\"):" + c.getClassLoader().getResource("/"));
        System.out.println("---------------------------");
        System.out.println("---------------------------");
        InputStream in1 = c.getResourceAsStream("AppTest.class");
        System.out.println(new String(FileUtil.readFromStream(in1)));
        try {
            in1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
