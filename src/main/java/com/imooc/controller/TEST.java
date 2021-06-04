package com.imooc.controller;

/**
 * @Author CGL
 * @Description:
 * @Date: Created in 10:48 2021/6/2
 * @Modified By: CGL
 */
public class TEST {
    public static void main(String[] args) {
        String a ="abc";
        String b ="abc";
        String c = new String("abc");
        String d = new String("abc");
        System.out.println("a==b:"+(a==b));
        System.out.println("a==c:"+(a==c));
        System.out.println("c==d:"+(c==d));
        System.out.println("a.equals(c):"+(a.equals(c)));
        System.out.println("c.equals(d):"+(c.equals(d)));
        System.out.println("c.equals(d):"+(c.equals(d)));
    }

}
