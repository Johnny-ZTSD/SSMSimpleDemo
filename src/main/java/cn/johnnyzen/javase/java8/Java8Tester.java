package cn.johnnyzen.javase.java8;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Java8Tester {
    // 使用 java 7 排序
    private void sortUsingJava7(List<String> names){
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    // 使用 java 8 排序
    private void sortUsingJava8(List<String> names){
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }

    public static void lambdaTest(){
        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");

        List<String> names2 = new ArrayList<String>();
        names2.add("Google ");
        names2.add("Runoob ");
        names2.add("Taobao ");
        names2.add("Baidu ");
        names2.add("Sina ");

        Java8Tester tester = new Java8Tester();
        System.out.println("使用 Java 7 语法: ");
        tester.sortUsingJava7(names1);
        System.out.println(names1);

        System.out.println("使用 Java 8 语法: ");
        tester.sortUsingJava8(names2);
        System.out.println(names2);
    }

    public static void main(String args[]){
        Java8Tester.lambdaTest();
    }
}
/*
$ javac Java8Tester.java
$ java Java8Tester
使用 Java 7 语法:
[Baidu , Google , Runoob , Sina , Taobao ]
使用 Java 8 语法:
[Baidu , Google , Runoob , Sina , Taobao ]
*/