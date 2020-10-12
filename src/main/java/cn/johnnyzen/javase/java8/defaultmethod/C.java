package cn.johnnyzen.javase.java8.defaultmethod;

/**
 * @project: SSMSimpleDemo
 * @author: Johnny
 * @date: 2020/10/12  09:19:02
 * @description: ...
 */

public class C extends D implements A, B{

    @Override
    public String hi() {
        return "C.hi";
    }

   @Override
    public String greet() {
        return "C.greet";
    }

    @Override
    public String hh() {
        return "C.hh";
    }

    /*
       默认方法(default void hello()) := 一个在接口里面有了一个实现的方法
       1. 子类优先继承父类的方法。
                如果父类没有相同签名的方法:
                    #1 子类才继承接口的默认方法;
                    #2 若子类实现了2个及以上的相同签名默认方法的接口时:
                        在子类中必须/强制 Override(重写) 默认方法; 否则，报编译错误
                如果父类中存在相同签名的方法:
                    #1 子类继承父类相同签名的方法；
                    #2 不论何种情况:
                        子类中不必再强制 Override(重写) 默认方法
     */
    /**
     * 重写 默认方法
     * 若子类不处理默认方法hello时，类C将编译出错，解决方式： 要么重新覆盖，要么指定实现父接口的该方法
     * 编译报错解决1：完全覆盖法
     */
/*    @Override
    public void hello() {
        System.out.println("C.hello");
    }*/
    /**
     * 重写 默认方法
     * 编译报错解决2：指定实现的父接口
     */
/*    @Override
    public void hello() {
        A.super.hello();
        B.super.hello();
    }*/
}
