package com.alan.hrsys.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestTask {
    @Async
    @Scheduled(cron = "*/2 * * * * ?")
    public void task1() throws InterruptedException {
        System.out.println("Task1开始，线程 id=>" + Thread.currentThread().getId());
        Thread.sleep(10000);
        System.out.println("Task1结束，线程 id=>" + Thread.currentThread().getId());
    }

    @Async
    @Scheduled(cron = "*/4 * * * * ?")
    public void task2() throws InterruptedException {
        System.out.println("Task2开始，线程 id=>" + Thread.currentThread().getId());
        Thread.sleep(2000);
        System.out.println("Task2结束，线程 id =>" + Thread.currentThread().getId());
    }


    @Scheduled(cron = "*/1 * * * * ?")
    public void taskA() throws InterruptedException {
        System.out.println("每隔一秒执行一次的任务");
    }
    @Async
    @Scheduled(cron = "* * */3 * * ?")
    public void taskB() throws InterruptedException {
        System.out.println("每隔三天执行一次的任务");
    }
}