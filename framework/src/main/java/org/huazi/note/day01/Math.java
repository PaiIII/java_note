package org.huazi.note.day01;

import org.huazi.note.common.User;

/**
 * 类加载过程：通过类加载器，把主类（main方法得类）加载到JVM。大致加载流程：
 * 加载-验证-准备-解析-初始化(Math.class:文本打开，二进制数据)
 *
 * @author huazi
 * @date 2022/2/19 10:37
 */
public class Math {
    public static final int initData = 666;
    public static User user = new User();

    public int compute() {//一个方法对应一块帧栈内存区域
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }


}
