package cn.johnnyzen.javase.java8.lambda;

import java.util.function.*;

public class LambdaTest {
    interface MathOperation {
        int operation(int a, int b);
    }
    interface GreetingService {
        void sayMessage(String message);
        //void sayMessage2(String message);
    }

    private int operate(int x, int y, MathOperation mathOperation){
        return mathOperation.operation(x, y);
    }

    public static void lambdaTest1(){
        // case1 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // case2 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // case3 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> { return a * b; };
        // case4 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        LambdaTest tester = new LambdaTest();
        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // case5 不用括号
        GreetingService greetService1 = message -> System.out.println("Hello " + message);
        //注： 必须保证 GreetingService 内仅有1个方法 sayMessage ，否则 编译器不能通过上述 lambda 表达式 自动定位到目标方法 => 报 编译错误

        // case6 用括号
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);
        //注: 同 case5

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");
    }

    public static void lambdaTest2(){
        Consumer<String> stringConsumer = System.out::println;
        stringConsumer.accept("618, 狂欢happy - objectName::instanceMethod");
        //等效
        Consumer<String> stringConsumer2 = (x) -> System.out.println(x);
        stringConsumer2.accept("618, 狂欢happy - lambda");
    }

    public static void lambdaTest3(){
        // Function : JDK1.8的新类/新特性
        //Integer: 函数的输入; String: 函数的输出/返回值
        Function<Integer, String> sf = String::valueOf;
        //等效
        Function<Integer, String> sf2 = (x) -> String.valueOf(x);

        String apply1 = sf.apply(61888);
        System.out.println("apply1:" + apply1);
        String apply2 = sf2.apply(61888);
        System.out.println("apply2:" + apply2);
    }

    public static void lambdaTest4(){
        //ClassName::instanceMethod  类的实例方法：把表达式的第一个参数当成instanceMethod的调用者，其他参数作为该方法的参数
        //BiPredicate也是JDK1.8的新类/新特性，它接受两个参数，并返回一个布尔值
        BiPredicate<String, String> sbp = String::equals;
        //等效
        BiPredicate<String, String> sbp2 = (x, y) -> x.equals(y);

        boolean test1 = sbp.test("a", "A");
        boolean test2 = sbp2.test("A", "a");
        boolean test3 = sbp2.test("b", "b");
        System.out.println(String.format("test1: %s / test2: %s / test3: %s", test1, test2, test3));
    }

    private static int count = 1;
    public static void lambdaTest5(){
        class User {//局部内部类
            private String content = null;
            public User(){ content = "init " + count; count++; };
            public void setContent(String content) {
                this.content = content;
            }
            @Override
            public String toString(){ return content; }
        }
        Supplier<User> us1 = User::new;//Supplier 也是JDK1.8的新类/新特性
        //等效
        Supplier<User> us2 = () -> new User();

        //获取对象
        User user1 = us1.get();
        User user2 = us2.get();
        System.out.println(String.format("user1: %s / user2: %s", user1, user2));
    }

    public static void lambdaTest6(){
        class User {//局部内部类
            private Integer id = null;
            private String name = null;
            public User(Integer id){ this.id = id; };
            public User(Integer id, String name){ this.id = id; this.name = name; };
            @Override
            public String toString(){ return String.format("id: %s / name: %s", id, name); }
        }
        //case1 一个参数,参数类型不同则会编译出错
        Function<Integer, User> uf1 = id -> new User(id);
        //或 加括号
        Function<Integer, User> uf2 = (id) -> new User(id);
        //等效
        Function<Integer, User> uf3 = (Integer id) -> new User(id);

        User apply1 = uf1.apply(618001);
        User apply2 = uf2.apply(618002);
        User apply3 = uf3.apply(618003);
        System.out.println(String.format("apply1: %s / apply2: %s / apply3: %s", apply1, apply2, apply3));

        //case2 两个参数
        BiFunction<Integer, String, User> ubf = (id, name) -> new User(id, name);//BiFunction 也是JDK1.8的新类/新特性
        User happy = ubf.apply(618, "狂欢happy");
        System.out.println(String.format("happy: %s", happy));
    }

    public static void main(String[] args) {
        //Java8Tester.lambdaTest();
        //LambdaTest.lambdaTest1();
        //LambdaTest.lambdaTest2();
        //LambdaTest.lambdaTest3();
        //LambdaTest.lambdaTest4();
        //LambdaTest.lambdaTest5();
        LambdaTest.lambdaTest6();
    }
}
/*
// 1. 不需要参数,返回值为 5
() -> 5

// 2. 接收一个参数(数字类型),返回其2倍的值
x -> 2 * x

// 3. 接受2个参数(数字),并返回他们的差值
(x, y) -> x – y

// 4. 接收2个int型整数,返回他们的和
(int x, int y) -> x + y

// 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
(String s) -> System.out.print(s);
*/
