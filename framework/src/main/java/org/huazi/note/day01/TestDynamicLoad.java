package org.huazi.note.day01;


/**
 * 逐步加载类、使用时才加载
 *
 * @author huazi
 * @date 2022/2/19 11:21
 */
public class TestDynamicLoad {
    static {
        System.out.println("*************1、load TestDynamicLoad************");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("*************4、load test************");
        B b = null; //b得输出不会执行，除非new
        B B2 = new B();
    }
}

class A {
    static {
        System.out.println("*************2、load A************");
    }

    public A() {
        System.out.println("*************3、initial A************");
    }
}

class B {
    static {
        System.out.println("*************5、load B************");
    }

    public B() {
        System.out.println("*************6、initial B************");
    }
}
