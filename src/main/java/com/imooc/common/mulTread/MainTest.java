package com.imooc.common.mulTread;

import org.springframework.scheduling.annotation.Async;

@Async
public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        for (int i =0; i<100;i++){
            if (i==10){
                Thread.sleep(1000);
            }
            System.out.println(Thread.currentThread()+"-->"+i);
        }
        new TreadTest().test();
    }
}
