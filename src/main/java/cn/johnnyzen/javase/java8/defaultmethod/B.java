package cn.johnnyzen.javase.java8.defaultmethod;

public interface B {

    String hi();

    String hh();

    default void hello() {
        System.out.println("B.hello");
    }

}
