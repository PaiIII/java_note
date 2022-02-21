package org.huazi.note.common;

import lombok.Data;

/**
 * 注释
 *
 * @author huazi
 * @date 2022/2/19 10:40
 */
@Data
public class User {

    private int id;

    private String name;

    static {
        System.out.println("*********************load User******************");
    }

    @Override
    protected void finalize() {
        //OOMtest.list.add(this);
        System.out.println("关闭资源，user" + id + "即将回收");
    }

    public void sout() {
        System.out.println("=======自己的加载器加载类调用方法=======");
    }


}
