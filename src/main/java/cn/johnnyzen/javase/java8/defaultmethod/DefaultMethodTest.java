package cn.johnnyzen.javase.java8.defaultmethod;

/**
 * @project: SSMSimpleDemo
 * @author: Johnny
 * @date: 2020/10/12  09:17:18
 * @description: 默认方法
 */
public class DefaultMethodTest {
    public static void main(String args[]){
        Vehicle vehicle = new DefaultMethodTest.Car();
        vehicle.print();
    }

    interface Vehicle {
        default void print(){//默认方法
            System.out.println("我是一辆车!");
        }

        static void blowHorn(){//静态(默认)方法
            System.out.println("按喇叭!!!");
        }
    }

    interface FourWheeler {
        default void print(){
            System.out.println("我是一辆四轮车!");
        }
    }

    static class Car implements Vehicle, FourWheeler {
        public void print(){
            Vehicle.super.print();
            FourWheeler.super.print();
            Vehicle.blowHorn();
            System.out.println("我是一辆汽车!");
        }
    }
}
/**
我是一辆车!
我是一辆四轮车!
按喇叭!!!
我是一辆汽车!
*/
