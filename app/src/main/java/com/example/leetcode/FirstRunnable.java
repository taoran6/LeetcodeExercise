package com.example.leetcode;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FirstRunnable implements Callable<Integer> {


    public static void main(String[] args) {
        //使用 Thread.currentThread() 获得当前线程的名字
        System.out.println("main:" + Thread.currentThread().getName());

        FirstRunnable callable = new FirstRunnable();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        try {
            int result = futureTask.get();
            System.out.println("result: " + result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        int i = 0;
        for(; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
        return i;
    }
}
