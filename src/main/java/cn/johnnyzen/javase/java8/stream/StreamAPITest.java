package cn.johnnyzen.javase.java8.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @project: SSMSimpleDemo
 * @author: Johnny
 * @date: 2020/10/12  10:04:12
 * @description:
 *     [深入浅出parallelStream - CSDN](https://blog.csdn.net/u011001723/article/details/52794455)
 */

public class StreamAPITest {
    public static void test1(){
        List<Widget> widgets = getWidgetsTestData();
        int sum =
                widgets.stream()
                        .filter(b -> {
                            return "RED".equals(b.getColor());
                        }) //filter 保留判断条件为true的对象
                        .sorted((x, y) -> x.getWeight() - y.getWeight())
                        .mapToInt(Widget::getWeight)
                        .sum();
        List<Widget> resultList =
                widgets.stream()
                        .filter(b -> {
                            return "RED".equals(b.getColor());
                        }).collect(Collectors.toList());
    }
    public static List<Widget> getWidgetsTestData() {
        // TODO
        return null;
    }

    public static void test2(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        numbers.stream().forEach(System.out::println); //stream 串行 顺序输出
        System.out.println("*********************");

        numbers.parallelStream().forEach(System.out::println);//parallelStream(forEach) 并行 / (每次运行均)无序输出
        System.out.println("######################");

        /**
         如果forEachOrdered()中间有其他如filter()的中介操作，会试着平行化处理，然后最终forEachOrdered()会以原数据顺序处理。
         因此，使用forEachOrdered()这类的有序处理,可能会（或完全失去）失去平行化的一些优势。实际上中介操作亦有可能如此，例如 sorted()方法
         */
        numbers.parallelStream().forEachOrdered(System.out::println);//parallelStream(forEachOrdered) 并行 / (每次运行均按照原顺序)有序输出
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
    }

    public static void test3(){
        Random random = new Random();
        random.ints().limit(10).forEach(
                System.out::println
        );
    }

    public static void test4(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        numbers.stream().forEach( b -> { b=b+1; } ); //直接操作原集合 并不会改变原来集合的元素值
        numbers.stream().forEach( System.out::println );
        System.out.println("********************");

        List<Integer> bakupNumbers = new ArrayList<>();
        numbers.stream().forEach( b -> { bakupNumbers.add(b+10); } );//通过遍历原集合时将新元素值拷贝到创建的新集合中
        bakupNumbers.stream().forEach( System.out::println );
    }

    static class User{
        private String name;
        private int age;
        public User(){  };
        public User(String name, int age){
            this.name = name;
            this.age = age;
        }
        public int getAge() { return this.age; }
        @Override
        public String toString() { return String.format("name: %s / age: %d", name, age); };
    }
    public static void testMap(){
        // case1 数字X列表 → sqrt(item)的数字Y列表
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map( i -> { return i*i;  }).distinct().collect(Collectors.toList());
        //List<Map<Integer, Integer>> squaresMapList = numbers.stream().map( (i) -> { i*i  }).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);

        // case2 字符串X列表 → String.toUpperCase(item) 的字符串Y列表
        List<String> alpha = Arrays.asList("a", "b", "c", "d");
        List<String> beta = alpha.stream().map(String::toUpperCase).collect(Collectors.toList());
        beta.forEach(item -> { System.out.println(item + " "); });

        //case3 对象列表 -> 对象属性(字符串/数值/...)列表
        List<User> users = Arrays.asList( new User("mkyong", 30), new User("jack", 27), new User("lawrence", 33) );
        List<String> names = users.stream().map(item -> item.name).collect(Collectors.toList());
        List<Integer> ages = users.stream().map(item -> item.age).collect(Collectors.toList());

        //case3 字符串 -> 对象列表 (扩展: 对象X列表 → 对象Y列表)
        List<String> names2 = Arrays.asList("johnny", "mike", "jane");
        List<User> users2 = names2.stream().map(name -> {
            User user = new User();
            user.name = name;
            user.age = (new Random()).nextInt();
            return user;
        }).collect(Collectors.toList());
    }

    public static void testFilter(){
        List<Integer> nums = Arrays.asList(1, 3, 5, 8, 9, 10);
        // 统计元素值大于4的元素个数
        long count = nums.stream().filter(item -> item > 4).count();
        // 对集合内满足条件的元素求和
//        int sum = nums.stream().filter(item -> item > 4).mapToInt().sum();
        System.out.println(String.format("count: %d", count));
    }

    public static void testLimit(){
        // 输出前10个元素
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
        // 输出前3个元素
        List<Integer> nums = Arrays.asList(1, 3, 5, 8, 9, 10);
        List<Integer> newNums = nums.stream().limit(3).collect(Collectors.toList());
        newNums.forEach(System.out::println);
    }

    /** 不借助 stream 语法的老方式
     list.sort(Comparator.comparing(Integer::intValue))
     list.sort(Comparator.comparing(Integer::intValue).reversed());
     list.sort(Comparator.comparing(Student::getAge));
     list.sort(Comparator.comparing(Student::getAge).reversed());
     */
    public static void testSorted(){
        List<Integer> nums = Arrays.asList(1,5,3,88,99,10);
        // case0 倒序 (并非降序， 倒序 := 对原序列逆序输出)
        /* Collections.reverse(nums); nums.stream().forEach(System.out::println); */
        //output: 10 99 88 3 5 1
        // case1 升序输出数值集合
        nums.stream().sorted().collect(Collectors.toList()).forEach(System.out::println);//等效于 下一行
        nums.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList()).forEach(System.out::println);
        //output: 1 3 5 10 88 99
        // case2 降序输出数值集合
        //注: 自然序逆序元素 -> sorted(Comparator.reverseOrder())
        nums.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).forEach(System.out::println);
        //output: 99 88 10 5 3 1

        // case3 按照用户年龄，升序排序，并输出
        //注: Comparator : public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor)
        List<User> users = Arrays.asList( new User("mkyong", 30), new User("jack", 27), new User("lawrence", 33) );
        users.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList()).forEach(System.out::println);
        //output: name: jack / age: 27 ; name: mkyong / age: 30 ; name: lawrence / age: 33
        // case4 按照用户年龄，降序排序，并输出 (在sorted内: 先升序 Comparator.comparing(User::getAge) ，再倒序 revered() )
        users.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList()).forEach(System.out::println);
        //output: name: lawrence / age: 33 ; name: mkyong / age: 30 ; name: jack / age: 27 ;
    }

    /**
     列表中最大的数 : 7
     列表中最小的数 : 2
     所有数之和 : 25
     平均数 : 3.5714285714285716
     2 2 3 3 3 5 7
     sum: 25
     */
    public static void testMapToInt(){ // mapToInt / mapToDouble / mapToLong => IntStream / DoubleStream / LongStream
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();//IntStream: summaryStatistics

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

        numbers.stream().mapToInt((x) -> x).sorted().forEach((x) -> { System.out.print(" "+ x);} );// IntStream: sorted()
        System.out.println("\nsum: " + numbers.stream().mapToInt((x) -> x).sum());// IntStream: sum()
    }

    public static void main(String[] args) {
        testMapToInt();
    }

}

enum COLOR {
    RED("RED", 101);

    private String code;
    private int value;

    COLOR(String code, int value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return this.code;
    }

    // TODO ...
}

class Widget {
    private String color;
    private Integer weight;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
