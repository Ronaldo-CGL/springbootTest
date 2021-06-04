package com.imooc.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StringToList {
    public static void main(String[] args) {
       //containRepeatChar();
        //listtest();
        /*int a = 10;
        int b = 3;
        System.out.println(!true&&true);*/
    }

    public static void containRepeatChar() {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        String str = "abadba";
        char[] elements = str.toCharArray();
        for (char e : elements) {
            if (map.containsKey(e)) {
                map.put(e, map.get(e) + 1);
            } else {
                map.put(e, 1);
            }
        }
//        for (Character character:map.keySet()) {
//            System.out.println("key:"+character+"--->有:"+map.get(character)+"个");
//        }
        map.forEach((key, value) -> {
            System.out.println(key + "：" + value);
        });
    }
    public static void listtest(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("c");
        strings.add("b");
        strings.add("d");
        //strings.forEach(s -> System.out.println(s));
        strings.forEach(s -> {
            if (s.equals("f")){
                System.out.println("ok");
            }
        });

    }
}
