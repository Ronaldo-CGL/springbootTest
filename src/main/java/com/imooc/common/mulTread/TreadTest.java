package com.imooc.common.mulTread;

import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;

public class TreadTest {
    @Async
    public void test() throws InterruptedException {
        new ArrayList<>();
        System.out.println("gg");
    }
}
