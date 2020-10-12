package cn.johnnyzen.javase.java8.defaultmethod;

public interface A {

    String hi();

    String greet();

    default void hello() {
        System.out.println("A.hello");
    }

}