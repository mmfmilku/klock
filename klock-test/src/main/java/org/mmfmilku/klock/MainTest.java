package org.mmfmilku.klock;

import org.mmfmilku.klock.file.FileUtil;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MainTest {

    public static void main(String[] args) {
        Class c = MainTest.class;
        System.out.println("java输出测试:");
        System.out.println(c.getResource(""));
        System.out.println(MainTest.class.getResource("/"));
        System.out.println(MainTest.class.getClassLoader().getResource(""));

    }

}
